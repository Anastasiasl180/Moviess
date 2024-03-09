package com.example.moviess.presentation.ui.signup_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.di.UserGlobalState
import com.example.moviess.domain.repository.AuthRepository
import com.example.moviess.presentation.ui.login_screen.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val globalState: UserGlobalState
) : ViewModel() {

    private val _signUp = Channel<SignInState>()
    val signUp = _signUp.receiveAsFlow()

    fun setUserName() {
        globalState.setUsername(name1.value)
    }
    val _nameOfUser: MutableState<String> = mutableStateOf("")
    val name1: State<String> = _nameOfUser

    fun setNameOfUser(name: String) {
        _nameOfUser.value = name
    }

    fun registerInUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signUp.send(SignInState(isSuccess = "Registration is success"))


                }

                is Resource.Loading -> {
                    _signUp.send(SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _signUp.send(SignInState(isError = result.message))
                }
            }
        }
    }

}


