package com.example.moviess.presentation.ui.login_screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.moviess.presentation.ui.theme.Pink46
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIpScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,


    ) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signIn.collectAsState(initial = null)

//    LaunchedEffect(key1 = stateSignIn.isError) {
//        stateSignIn.isError?.let { error ->
//            Toast.makeText(
//                context,
//                error,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                scope.launch {
                    val signInResult = viewModel.signInByGoogle.getSignInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink46)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Log in",
                Modifier.padding(top = 100.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.White,
                fontFamily = FontFamily.Default
            )

            TextField(
                value = email,
                onValueChange = {
                    email = it
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
                    Text(text = "Email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = password, onValueChange = {
                password = it
            }, modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedPlaceholderColor = Color.Black,
                ), shape = RoundedCornerShape(10.dp), singleLine = true, placeholder = {
                    Text(text = "Password")


                })
            Button(
                onClick = {
                    viewModel.logInUser(email, password, callbackIfSuccess = {
                        navigateToHome()

                    })

                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.44f)
                    .padding(top = 110.dp, start = 30.dp, end = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red, containerColor = Pink44

                ),
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text(text = "LogIn", color = Color.DarkGray, modifier = Modifier.padding(7.dp))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//                if (state.value?.isLoading == true) {
//                    CircularProgressIndicator()
//
//                }
            }


            Text(
                text = "or connect with",
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 100.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    scope.launch {
                        val signInIntentSender = viewModel.signInByGoogle.signInByGoogle()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )

                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )

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