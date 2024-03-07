package com.example.moviess.presentation.ui.changesInProfile.EditingScreens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.shadow
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
fun ChangingUserName(viewModel: SignInViewModel,onClickBack: () -> Unit) {

    var oldUserName by remember {
        mutableStateOf(viewModel.globalState.username.value)
    }
    var oldImage by remember {
        mutableStateOf(
            viewModel.globalState.bitmapImage.value
        )
    }
    val showDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful && result.uriContent != null) {
            oldImage = convertUriToImage(result.uriContent!!, context)
        } else {
            val exception = result.error
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {
        IconButton(onClick = { onClickBack() }) {
            Icon(
                imageVector = Icons.Sharp.Close, contentDescription = "",
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
        }
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
                    .background(Color.Black)
                    .size(40.dp)
                    .padding(10.dp)
                    .clickable {
                        val cropOption = CropImageContractOptions(
                            CropImage.CancelledResult.uriContent,
                            CropImageOptions()
                        )
                        imageCropLauncher.launch(cropOption)

                    }


            )
            Card(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .shadow(16.dp, ambientColor = Color.LightGray)
                    .height(250.dp)
                    .width(350.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            ) {


                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    Alignment.Center
                ) {

                    Row(modifier = Modifier.padding(top = 30.dp)) {
                        Text(text = "Change your name from ${viewModel.globalState.username.value} to")

                    }
                }

                TextField(value = oldUserName,
                    onValueChange = {
                        oldUserName = it
                    },
                    modifier = Modifier
                        .padding(start = 10.dp, top = 30.dp)
                        .width(250.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Black,
                        focusedPlaceholderColor = Color.Black
                    ), singleLine = true, placeholder = {
                        Text(text = "New username")
                    })
                TextButton(modifier = Modifier.padding(top = 50.dp, start = 260.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    onClick = {
                        showDialog.value = true
                    }
                ) {
                    Text(text = "Change")

                }
                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog.value = false
                        },
                        title = {
                            Text(text = "Save changes?")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val newPerson =
                                        viewModel.globalState.getNewPersonMapForMoviesId(
                                            oldUserName,
                                            oldImage?.let { viewModel.globalState.bitMapToString(it) }
                                        )
                                    viewModel.globalState.updatePerson(newPerson)
                                    showDialog.value = true
                                },
                                modifier = Modifier.padding(top = 60.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                            ) {
                                Text(text = "Yes")
                            }

                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDialog.value = false
                                },
                                modifier = Modifier.padding(top = 60.dp, end = 20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                            ) {
                                Text("Cancel")
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.size(300.dp, 200.dp)
                    )
                }
                /*  TextButton(modifier = Modifier.padding(top = 50.dp, start = 260.dp),
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

                  }*/

            }


        }


    }
}
