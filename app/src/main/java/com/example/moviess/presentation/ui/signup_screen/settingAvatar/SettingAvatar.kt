package com.example.moviess.presentation.ui.signup_screen.settingAvatar

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.canhub.cropper.CropImage.CancelledResult.uriContent
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.moviess.R
import com.example.moviess.presentation.ui.login_screen.SignInViewModel


@Composable
fun Avatar(
    viewModel: SignInViewModel,
    navigateToHome: () -> Unit
) {

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful && result.uriContent != null) {
            viewModel.globalState.saveImage(result.uriContent!!)
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
                        .size(150.dp)

                )

            }


            Image(painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 90.dp)
                    .offset(y = -35.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .size(50.dp)
                    .padding(10.dp)
                    .clickable {
                        val cropOption = CropImageContractOptions(uriContent, CropImageOptions())
                        imageCropLauncher.launch(cropOption)

                    }


            )
            Button(onClick = {
                viewModel.globalState.saveUser(callback = {
                    navigateToHome()
                })


            }) {
                Text(text = "Continue")
            }
        }
    }

}


