package com.codeofmario.blogapp.domain.usecase.auth

import android.util.Log
import com.codeofmario.blogapp.domain.entity.Login
import com.codeofmario.blogapp.domain.entity.Tokens
import com.codeofmario.blogapp.domain.entity.UserInfo
import com.codeofmario.blogapp.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {
    override suspend fun isAuthenticated(): Boolean {
        return try {
            authRepository.me()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun login(login: Login): Tokens {
        val tokens = authRepository.login(login)
        authRepository.saveAccessToken(tokens.accessToken)
        authRepository.saveRefreshToken(tokens.refreshToken)
        return tokens
    }

    override suspend fun logout() {
        authRepository.logout()
        authRepository.removeAccessToken()
        authRepository.removeRefreshToken()
    }

    override suspend fun me(): UserInfo {
        return authRepository.me()
    }

    override suspend fun refresh(refreshToken: String): Tokens {
        val tokens = authRepository.refresh(refreshToken)
        Log.d("HAHA", tokens.toString())
        authRepository.saveAccessToken(tokens.accessToken)
        authRepository.saveRefreshToken(tokens.refreshToken)
        return tokens
    }
}