package com.example.moviess.presentation.ui.login_screen.reset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.moviess.presentation.ui.login_screen.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetScreen(viewModel: SignInViewModel) {

    var email by rememberSaveable { mutableStateOf("") }
    Column {
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "email") }
        )
        TextButton(onClick = { viewModel.sendResetCode(email) }) {
            Text(text = "Reset")
    }


  }


}