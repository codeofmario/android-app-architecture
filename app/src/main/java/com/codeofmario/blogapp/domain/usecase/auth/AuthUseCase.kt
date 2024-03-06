package com.codeofmario.blogapp.domain.usecase.auth

import com.codeofmario.blogapp.domain.entity.Login
import com.codeofmario.blogapp.domain.entity.Tokens
import com.codeofmario.blogapp.domain.entity.UserInfo

interface AuthUseCase {
    suspend fun isAuthenticated(): Boolean
    suspend fun login(login: Login): Tokens
    suspend fun logout(): Unit
    suspend fun me(): UserInfo
    suspend fun refresh(refreshToken: String): Tokens
}