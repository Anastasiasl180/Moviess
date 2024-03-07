package com.example.moviess.presentation.ui.login_screen.reset

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun ResetScreen(viewModel: SignInViewModel, backToSignIn: () -> Unit) {

    var email by rememberSaveable { mutableStateOf("") }
    val showDialog = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {

        Column(
            modifier = Modifier.padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 10.dp)
                    .shadow(37.dp, ambientColor = Color.LightGray),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent),
                border = BorderStroke(width = 1.dp, color = Color.Transparent)
            ) {


                Box() {
                    Text(
                        text = "To reset your password, enter your email you entered during registration," +
                                "you will receive an email to this email address where you can change your password"
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(150.dp)
                        .shadow(27.dp, ambientColor = Color.LightGray)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.TextField(
                            value = email,
                            onValueChange = { email = it },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                cursorColor = Color.Black,
                                focusedIndicatorColor = Color.Black,
                                focusedPlaceholderColor = Color.LightGray,
                                focusedLabelColor = Color.LightGray
                            ),
                            singleLine = true,
                            placeholder = {
                                Text(text = "Your gmail")
                            }
                        )


                    }

                }


                TextButton(
                    onClick = {
                        showDialog.value = true
                        viewModel.sendResetCode(email)
                    },
                    modifier = Modifier.padding(top = 40.dp), shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Reset")
                }
                if (showDialog.value) {
                    AlertDialog(onDismissRequest = { showDialog.value = false }, title = {
                        Text(text = "We have sent you a password change form, please check your email")
                    }, confirmButton = {
                        TextButton(onClick = { backToSignIn() }) {
                            Text(text = "Ok")
                        }


                    })
                }

            }
        }


    }
}