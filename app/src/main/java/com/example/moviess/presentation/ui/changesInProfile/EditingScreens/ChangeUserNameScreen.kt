package com.example.moviess.presentation.ui.changesInProfile.EditingScreens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.moviess.R
import com.example.moviess.di.convertUriToImage
import com.example.moviess.presentation.ui.login_screen.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangingUserName(viewModel: SignInViewModel) {

    var oldUserName by remember {
        mutableStateOf(viewModel.globalState.username.value)
    }
    var oldImage by remember {
        mutableStateOf(
            viewModel.globalState.bitmapImage.value
        )
    }
    val context = LocalContext.current
    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful && result.uriContent != null) {
            oldImage = convertUriToImage(result.uriContent!!, context)
        } else {
            val exception = result.error
        }
    }


    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        )
        {
            if (oldImage != null) {
                Image(
                    bitmap = oldImage!!.asImageBitmap(),
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
            Card {
                Text(text = viewModel.globalState.username.value)
            }

            TextField(value = oldUserName,
                onValueChange = {
                    oldUserName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    focusedPlaceholderColor = Color.Black
                ), shape = RoundedCornerShape(10.dp), singleLine = true, placeholder = {
                    Text(text = "New username")
                })

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    val newPerson =
                        viewModel.globalState.getNewPersonMapForMoviesId(
                            oldUserName,
                            oldImage?.let { viewModel.globalState.bitMapToString(it) }
                        )
                    viewModel.globalState.updatePerson(newPerson)
                }
            ) {
                Text(text = "Change")

            }


        }
    }
}