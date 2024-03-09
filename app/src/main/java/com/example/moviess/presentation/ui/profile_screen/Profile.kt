package com.example.moviess.presentation.ui.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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


@Composable
fun ScreenOfProfile(
    viewModel: SignInViewModel,
    onClickBack: () -> Unit,
    navigateToChangePassword: () -> Unit,
    navigateToChangeUserName: () -> Unit,
    navigateToSignUp: () -> Unit
) {

    val oldImage by remember {
        mutableStateOf(
            viewModel.globalState.bitmapImage.value
        )
    }
    val showDialog = remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.37f)
                .background(color = Color.Gray)
        ) {

            IconButton(onClick = { onClickBack() }) {
                Icon(imageVector = Icons.Sharp.Close, contentDescription = "")

            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                if (oldImage != null) {
                    Image(
                        bitmap = oldImage!!.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
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


                Text(
                    text = "Anastasia" /*viewModel.globalState.username.value*/,
                    modifier = Modifier.padding(top = 20.dp),
                    style = MaterialTheme.typography.headlineSmall
                )

                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false }, title = {
                            Text(text = "Delete account?")
                        },

                        confirmButton = {
                            Button(
                                onClick = {
                                    viewModel.globalState.deletePerson()
                                    navigateToSignUp()
                                    showDialog.value = true
                                }, modifier = Modifier.padding(top = 60.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                            ) {
                                Text(text = "Yes")
                            }

                        }, dismissButton = {
                            Button(
                                onClick = { showDialog.value = false },
                                modifier = Modifier.padding(top = 60.dp, end = 20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                            ) {
                                Text("Cancel")
                            }
                        }
                    )

                }

                TextButton(onClick = {
                    showDialog.value = true

                }) {
                    Text(text = "Delete account")
                }
            }

            ElevatedCard(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .shadow(16.dp, ambientColor = Color.LightGray)
                    .height(150.dp)
                    .width(350.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {


                        Row(
                            modifier = Modifier

                                .padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.Person,
                                contentDescription = "",
                                tint = Color.Gray
                            )

                            Text(
                                text = "Text1",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = Color.Black
                            )
                            IconButton(onClick = { }, modifier = Modifier.padding(start = 180.dp)) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }

                        }
                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Row(
                        modifier = Modifier

                            .padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Person,
                            contentDescription = "",
                            tint = Color.Gray
                        )

                        Text(
                            text = "Text1",
                            modifier = Modifier
                                .padding(start = 10.dp),
                            color = Color.Black
                        )
                        IconButton(onClick = { }, modifier = Modifier.padding(start = 180.dp)) {

                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.Black
                            )

                        }

                    }


                }

            }

            ElevatedCard(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .shadow(16.dp, ambientColor = Color.LightGray)
                    .height(290.dp)
                    .width(350.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(modifier = Modifier.fillMaxWidth()) {


                        Row(
                            modifier = Modifier

                                .padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.Person,
                                contentDescription = "",
                                tint = Color.Gray
                            )

                            Text(
                                text = "Username",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = Color.Black
                            )
                            IconButton(onClick = { navigateToChangeUserName()}, modifier = Modifier.padding(start = 145.dp)) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }

                        }
                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )


                    Row(
                        modifier = Modifier

                            .padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Email,
                            contentDescription = "",
                            tint = Color.Gray
                        )

                        Text(
                            text = "Email and password",
                            modifier = Modifier
                                .padding(start = 10.dp),
                            color = Color.Black
                        )
                        IconButton(onClick = { navigateToChangePassword()}, modifier = Modifier.padding(start = 70.dp)) {

                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.Black
                            )

                        }

                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Row(
                        modifier = Modifier

                            .padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Notifications,
                            contentDescription = "",
                            tint = Color.Gray
                        )

                        Text(
                            text = "Notifications",
                            modifier = Modifier
                                .padding(start = 10.dp),
                            color = Color.Black
                        )
                        IconButton(onClick = { }, modifier = Modifier.padding(start = 125.dp)) {

                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.Black
                            )

                        }

                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )


                    Row(
                        modifier = Modifier

                            .padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.LocationOn,
                            contentDescription = "",
                            tint = Color.Gray
                        )

                        Text(
                            text = "Language",
                            modifier = Modifier
                                .padding(start = 10.dp),
                            color = Color.Black
                        )
                        IconButton(onClick = { }, modifier = Modifier.padding(start = 150.dp)) {

                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.Black
                            )

                        }

                    }


                }
            }
        }
    }
}
/*
@Composable
fun Item (field:String,icon:ImageVector) {

    Row(
        modifier = Modifier

            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Color.Gray
        )

        Text(
            text = field,
            modifier = Modifier
                .padding(start = 10.dp),
            color = Color.Black
        )
        IconButton(onClick = { }, modifier = Modifier.padding(start = 150.dp)) {

            Icon(
                imageVector = Icons.Sharp.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black
            )

        }

    }
}*/

