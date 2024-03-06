package com.codeofmario.blogapp.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeofmario.blogapp.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {
    fun isLoggedIn(navigate: (isLoggedIn: Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                useCase.me()
                navigate(true)
            } catch (e: Exception) {
                navigate(false)
            }
        }
    }
}