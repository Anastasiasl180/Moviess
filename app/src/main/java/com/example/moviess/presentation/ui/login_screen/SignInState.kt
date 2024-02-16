package com.example.moviess.presentation.ui.login_screen

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",

    val isSignInSuccess:Boolean = false
)