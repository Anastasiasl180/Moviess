package com.example.moviess.presentation.ui.signup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.di.SignInResult
import com.example.moviess.di.UserGlobalState
import com.example.moviess.domain.repository.AuthRepository
import com.example.moviess.presentation.ui.login_screen.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val globalState: UserGlobalState
) : ViewModel() {

  private  val _signUp = Channel<SignInState>()
    val signUp = _signUp.receiveAsFlow()

    val name get() = globalState.username
    fun setUserName(name:String){
        globalState.setUsername(name)
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


