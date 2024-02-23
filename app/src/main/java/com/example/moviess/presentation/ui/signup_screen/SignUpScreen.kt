package com.example.moviess.presentation.ui.signup_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviess.presentation.ui.theme.Pink44
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
    navigateToAvatar: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUp.collectAsState(initial = null)

    var isValid by remember { mutableStateOf(false) }
    var isValid2 by remember { mutableStateOf(false) }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return email.trim().matches(emailRegex)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Sign up",
                Modifier.padding(top = 100.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily.Default
            )

            TextField(

                value = email,
                onValueChange = {
                    email = it
                    isValid = email.isNotEmpty() && isValidEmail(it)


                },
                isError = !isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 85.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    focusedPlaceholderColor = Color.Black
                ), shape = RoundedCornerShape(10.dp), singleLine = true, placeholder = {
                    Text(text = "Email")
                }
            )
            if (!isValid && !isValidEmail(email)) {
                Text(text = "Please enter valid text", color = Color.Red)

            }
            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = password, onValueChange = {
                password = it
                isValid2 = password.length > 8
            },
                isError = !isValid2,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedPlaceholderColor = Color.Black,
                ), shape = RoundedCornerShape(10.dp), singleLine = true, placeholder = {
                    Text(text = "Password")


                })
            if (!isValid2) {
                Text(text = "Please enter 8 or more characters", color = Color.Red)
            }
            TextField(value = viewModel.name.value, onValueChange = {
                viewModel.setUserName(it)
            }, modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedPlaceholderColor = Color.Black,
                ), shape = RoundedCornerShape(10.dp), singleLine = true, placeholder = {
                    Text(text = "Name")


                })

            Button(
                onClick = {
                    scope.launch {
                        if (email.isNotEmpty() && password.length >= 8 && isValidEmail(email)) {
                            viewModel.registerInUser(email, password)
                            navigateToAvatar()
                        } else {
                            if (email.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please enter your email address",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (password.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please enter your password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (!isValidEmail(email)) {
                                Toast.makeText(
                                    context,
                                    "You missed the required characters symbols",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }

                    }


                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.44f)
                    .padding(top = 110.dp, start = 30.dp, end = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red, containerColor = Pink44

                ),
                shape = RoundedCornerShape(10.dp)
            )

            {
                Text(text = "SignUp", color = Color.White, modifier = Modifier.padding(7.dp))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//                if (state.value?.isLoading == true) {
//                   // CircularProgressIndicator()
//
//                }
            }
            Column(modifier = Modifier.padding(top = 45.dp, end = 130.dp)) {
                Text(
                    text = "Already have an account? ", fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Text(
                        text = "Sign in",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    IconButton(onClick = { navigateToSignIn() }) {
                        Icon(
                            imageVector = Icons.Sharp.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .fillMaxSize(0.55f)
                                .padding(bottom = 5.dp)
                        )

                    }
                }




                LaunchedEffect(key1 = state.value?.isSuccess) {
                    scope.launch {
                        if (state.value?.isSuccess?.isNotEmpty() == true) {
                            val success = state.value?.isSuccess
                            Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                LaunchedEffect(key1 = state.value?.isError) {
                    scope.launch {
                        if (state.value?.isError?.isNotEmpty() == true) {
                            val error = state.value?.isError
                            Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

}