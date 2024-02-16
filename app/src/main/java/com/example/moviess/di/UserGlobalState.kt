package com.example.moviess.di

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserGlobalState @Inject constructor(
    private val context: Context,
    private val auth: FirebaseAuth,
    private val oneTapClient: SignInClient

) {
    private val personCollectionRef = Firebase.firestore.collection("users")

    private val _bitmapImage: MutableState<Bitmap?> = mutableStateOf(null)
    val bitmapImage: State<Bitmap?> = _bitmapImage

    private val _username: MutableState<String> = mutableStateOf("")
    val username: State<String> = _username

    fun setUsername(name: String) {
        _username.value = name
    }


    fun saveUser(callback: () -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val bos = ByteArrayOutputStream()
            bitmapImage.value?.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val bArray = bos.toByteArray()
            val user = User(bArray.contentToString(), username.value, uid = auth.uid!!)
            personCollectionRef.add(user).await()
            withContext(Dispatchers.Main) {
                callback()
            }


        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }

    fun getUser(callback: () -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = personCollectionRef.get().await()

            for (document in querySnapshot.documents) {
                val person = document.toObject<User>()
                if (person?.uid == auth.uid) {
                    withContext(Dispatchers.Main) {
                        _username.value = person?.name ?: ""
                        person?.image?.let {
                            _bitmapImage.value = convertStringToImage(it)

                        }
                        callback()
                    }

                    break
                }

            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

        }
    }

    fun getNewPersonMap(oldName:String,oldImage:Bitmap): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        if (oldName.isNotEmpty()) {
            map["username"] = oldName
        }
       oldImage.let { bitmap ->
            val bos = ByteArrayOutputStream()
           oldImage.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val bArray = bos.toByteArray()
            val encodedString = (bArray.contentToString())
            map["bitmapImage"] = encodedString

        }
        return map

    }

    fun updatePerson(newPersonMap: Map<String, Any>) =
        CoroutineScope(Dispatchers.IO).launch {
            val peronQuerry = personCollectionRef
                .whereEqualTo("username", username.value)
                .whereEqualTo("image", bitmapImage.value)
                .whereEqualTo("id", auth.uid)
                .get()
                .await()
            if (peronQuerry.documents.isNotEmpty()) {
                for (document in peronQuerry) {
                    try {
                        personCollectionRef.document(document.id)
                            .set(newPersonMap, SetOptions.merge()).await()
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    fun saveImage(image: Uri) {

        _bitmapImage.value = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, image)

        } else {
            val source = ImageDecoder.createSource(context.contentResolver, image)
            ImageDecoder.decodeBitmap(source)
        }
    }

    private fun convertStringToImage(image: String): Bitmap {
        val list = image.substring(1, image.length - 1).split(", ").map {
            it.toInt()

        }
        val byteArray = ByteArray(list.size)
        for (i in list.indices) {
            byteArray[i] = list[i].toByte()

        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    }
}


data class SignInResult(
    val data: UserFromGoogle?,
    val errorMessage: String?
)

data class UserFromGoogle(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)

data class User(
    val image: String?,
    val name: String,
    val uid: String
) {
    constructor() : this(null, "", "")
}
