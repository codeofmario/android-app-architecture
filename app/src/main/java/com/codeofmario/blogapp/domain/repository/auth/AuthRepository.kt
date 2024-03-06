package com.codeofmario.blogapp.domain.repository.auth

import com.codeofmario.blogapp.domain.entity.Login
import com.codeofmario.blogapp.domain.entity.Tokens
import com.codeofmario.blogapp.domain.entity.UserInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun isAuthenticated(): Boolean
    suspend fun login(login: Login): Tokens
    suspend fun logout()
    suspend fun me(): UserInfo
    suspend fun refresh(refreshToken: String): Tokens

    fun getAccessToken(): Flow<String?>
    suspend fun saveAccessToken(token: String)
    suspend fun removeAccessToken()

    fun getRefreshToken(): Flow<String?>
    suspend fun saveRefreshToken(token: String)
    suspend fun removeRefreshToken()
}