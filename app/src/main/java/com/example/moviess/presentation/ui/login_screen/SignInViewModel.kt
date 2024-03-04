package com.example.moviess.presentation.ui.login_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviess.common.Resource
import com.example.moviess.di.SignInResult
import com.example.moviess.di.UserGlobalState
import com.example.moviess.domain.repository.AuthRepository
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
    val globalState: UserGlobalState,
    private val auth: FirebaseAuth,
) : ViewModel() {

    val _signIn = Channel<SignInState>()
    val signIn = _signIn.receiveAsFlow()

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()


    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccess = result.data != null,
                isError = result.errorMessage
            )

        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    fun logInUser(email: String, password: String, callbackIfSuccess: () -> Unit) =
        viewModelScope.launch {
            repository.loginUser(email, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _signIn.send(SignInState(isSuccess = "Sign in Success"))
                        globalState.getUser(callbackIfSuccess)
                    }

                    is Resource.Loading -> {
                        _signIn.send(SignInState(isLoading = true))
                    }

                    is Resource.Error -> {
                        _signIn.send(SignInState(isError = result.message))
                    }
                }
            }
        }
    fun sendResetCode(email: String) = viewModelScope.launch {
        auth.sendPasswordResetEmail(email)

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModelScope.launch { _signIn.send(SignInState(isSuccess = "Reset email sent")) }

                } else {
                    viewModelScope.launch { _signIn.send(SignInState(isError = task.exception?.message)) }
                }

            }
    }
    fun updatePassword(currentPassword:String, newPassword:String) = viewModelScope.launch {
        try {
            val user = auth.currentUser
            if (user!=null){
                val credential = EmailAuthProvider.getCredential(user.email!!,currentPassword)
                user.reauthenticate(credential).await()
            }else {
                return@launch
            }
            auth.currentUser!!.updatePassword(newPassword).await()
            _signIn.send(SignInState(isSuccess = "Password updated successfully"))
        }catch (e:Exception){
            _signIn.send(SignInState(isError = e.message))
        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun verifyAndChangePassword(email: String, currentPassword: String, newPassword: String)=
        viewModelScope.launch {
        val user = auth.currentUser
            if (user == null){
                _signIn.send(SignInState(isError = "No user with this data"))
                return@launch
            }

            val credential = EmailAuthProvider.getCredential(email,currentPassword)
            try {
                Log.wtf("","111")
                user.reauthenticate(credential).await()
                Log.wtf("","222")
                updatePassword(currentPassword, newPassword)
                Log.wtf("","333")
                Log.wtf("",newPassword)
            }catch (e:FirebaseAuthInvalidCredentialsException){
                _signIn.send(SignInState(isError = "Incorrect password"))
            } catch (e:Exception){
                _signIn.send(SignInState(isError = e.message))
            }
    }

}