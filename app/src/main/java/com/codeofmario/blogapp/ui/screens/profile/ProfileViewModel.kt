package com.codeofmario.blogapp.ui.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeofmario.blogapp.domain.entity.UserInfo
import com.codeofmario.blogapp.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {
    private val _userInfo = MutableLiveData<UserInfo>(null)
    val userInfo : LiveData<UserInfo> = _userInfo

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                _userInfo.value = useCase.me()
            } catch (_: Exception) {

            }
        }
    }
    fun logout(onComplete: (success: Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                useCase.logout()
                onComplete(true)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }
}