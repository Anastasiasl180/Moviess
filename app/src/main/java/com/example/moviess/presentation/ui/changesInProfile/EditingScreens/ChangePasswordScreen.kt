package com.example.moviess.presentation.ui.changesInProfile.EditingScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviess.presentation.ui.login_screen.SignInViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePassword(viewModel: SignInViewModel) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        TextField(value = email,
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
            })

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
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = newPassword, onValueChange = {
            newPassword = it
        }, modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedPlaceholderColor = Color.Black,
            ), shape = RoundedCornerShape(10.dp), singleLine = true, placeholder = {
                Text(text = "New password")


            })


        TextButton(onClick = { viewModel.verifyAndChangePassword(email, password, newPassword) }) {
            Text(text = "Change")
        }
    }

}


