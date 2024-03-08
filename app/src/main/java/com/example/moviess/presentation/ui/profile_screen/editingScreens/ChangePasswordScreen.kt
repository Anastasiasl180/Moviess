package com.example.moviess.presentation.ui.profile_screen.editingScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviess.presentation.ui.login_screen.SignInViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePassword(viewModel: SignInViewModel, onClickBack: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable {
        mutableStateOf("")
    }
    val showDialog = remember {
        mutableStateOf(false)
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 70.dp)
                    .shadow(27.dp, ambientColor = Color.LightGray),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent),
                border = BorderStroke(width = 1.dp, color = Color.Transparent)
            ) {


                Box() {
                    Text(text = "To change your password, enter the email you entered during registration, and your old password*")
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .shadow(16.dp, ambientColor = Color.LightGray)
                    .height(350.dp)
                    .width(350.dp),

                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {


                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            focusedPlaceholderColor = Color.LightGray,
                            focusedLabelColor = Color.LightGray
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        singleLine = true,
                        placeholder = {
                            Text(text = "Your gmail")
                        }
                    )

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.LightGray,
                            focusedIndicatorColor = Color.Black,
                            focusedPlaceholderColor = Color.LightGray
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        singleLine = true,
                        placeholder = {
                            Text(text = "Your password")
                        }
                    )

                    TextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.LightGray,
                            focusedIndicatorColor = Color.Black,
                            focusedPlaceholderColor = Color.LightGray
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        singleLine = true, placeholder = {
                            Text(text = "New password")
                        }
                    )


                }
            }
            if (showDialog.value) {
                AlertDialog(onDismissRequest = { showDialog.value = false }, title = {
                    Text(text = "Save changes?")
                }, confirmButton = {
                    viewModel.verifyAndChangePassword(
                        email,
                        password,
                        newPassword
                    )
                    showDialog.value = true
                }, modifier = Modifier, dismissButton = {
                    Button(
                        onClick = {
                            showDialog.value = false
                        },
                        modifier = Modifier.padding(top = 60.dp, end = 20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("Cancel")
                    }

                })
            }
            TextButton(
                onClick = { showDialog.value = true },
                modifier = Modifier.padding(top = 40.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Change")
            }
        }

    }

}


