package com.codeofmario.blogapp.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeofmario.blogapp.domain.entity.Login
import com.codeofmario.blogapp.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {
    private val _login = MutableLiveData(initLogin())
    val login: LiveData<Login> = _login


    fun onUsernameChanged(username: String) {
        _login.value = _login.value?.copy(username = username)
    }

    fun onPasswordChanged(password: String) {
        _login.value = _login.value?.copy(password = password)
    }

    suspend fun login(): Boolean {
        return try {
            useCase.login(login.value!!)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun initLogin(): Login {
        return Login(
            username = "",
            password = ""
        )
    }
}