package com.codeofmario.blogapp.data.local.source.auth

import kotlinx.coroutines.flow.Flow

interface LocalAuthDataSource {
    fun getAccessToken(): Flow<String?>
    suspend fun saveAccessToken(token: String)
    suspend fun removeAccessToken()

    fun getRefreshToken(): Flow<String?>
    suspend fun saveRefreshToken(token: String)
    suspend fun removeRefreshToken()
}