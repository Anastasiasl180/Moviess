package com.example.moviess.presentation.ui.changesInProfile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.moviess.R
import com.example.moviess.presentation.ui.login_screen.SignInViewModel

@Composable
fun Scaffold(content: @Composable () -> Unit) {

    androidx.compose.material3.Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {

        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()


            }

        }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenOfProfile(viewModel: SignInViewModel) {

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful && result.uriContent != null) {
            viewModel.globalState.saveImage(result.uriContent!!)
        } else {
            val exception = result.error
        }
    }

    val oldUserName by remember {
        mutableStateOf(viewModel.globalState.username.value)
    }
    val oldImage by remember {
        mutableStateOf(
            viewModel.globalState.bitmapImage.value
        )
    }

    var openDialog by remember { mutableStateOf(false) }

   fun convertStringToImage(image: String): Bitmap {
        val list = image.substring(1, image.length - 1).split(", ").map {
            it.toInt()

        }
        val byteArray = ByteArray(list.size)
        for (i in list.indices) {
            byteArray[i] = list[i].toByte()

        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    }

    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        )
        {
            if (viewModel.globalState.bitmapImage.value != null) {
                Image(
                    bitmap = viewModel.globalState.bitmapImage.value?.asImageBitmap()!!,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black)
                        .size(100.dp)

                )

            }
            Image(painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 90.dp)
                    .offset(y = -35.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
                    .size(50.dp)
                    .padding(10.dp)
                    .clickable {
                        val cropOption = CropImageContractOptions(
                            CropImage.CancelledResult.uriContent,
                            CropImageOptions()
                        )
                        imageCropLauncher.launch(cropOption)

                    }


            )
            ElevatedCard(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(300.dp), elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ), colors = CardDefaults.cardColors(containerColor = Color.DarkGray)


            ) {


                Column(
                    modifier = Modifier
                        .padding(top = 29.dp)
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {


                    Card(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .clickable { openDialog = true }) {
                        Text(text = "Text", modifier = Modifier, color = Color.Black)
                        //viewModel.globalState.username.value)

                    }
                    Card(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .clickable { }) {
                        Text(text = "Text", modifier = Modifier, color = Color.Black)


                    }
                    Card(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .clickable { }) {
                        Text(text = "Text", modifier = Modifier, color = Color.Black)


                    }


                }
            }
            if (openDialog) {
                AlertDialog(onDismissRequest = { openDialog = false },

                    dismissButton = {
                        IconButton(
                            onClick = { openDialog = false },
                            modifier = Modifier
                                .background(color = Color.Black)
                                .fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "image",
                                modifier = Modifier.padding(start = 200.dp, top = 20.dp)
                            )

                        }
                    }, confirmButton = {
                        TextButton(onClick = {

                            //logic
                            openDialog = false
                        }, modifier = Modifier.padding(top = 300.dp)) {
                            Text(text = "Change")

                        }
                    }, modifier = Modifier
                        .width(400.dp)
                        .height(400.dp), containerColor = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}
