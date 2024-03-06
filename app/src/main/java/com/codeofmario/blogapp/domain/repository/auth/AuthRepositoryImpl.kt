package com.codeofmario.blogapp.domain.repository.auth

import com.codeofmario.blogapp.data.local.source.auth.LocalAuthDataSource
import com.codeofmario.blogapp.data.remote.source.auth.RemoteAuthDataSource
import com.codeofmario.blogapp.domain.entity.Login
import com.codeofmario.blogapp.domain.entity.Tokens
import com.codeofmario.blogapp.domain.entity.UserInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) : AuthRepository {
    override suspend fun isAuthenticated(): Boolean {
        return !localAuthDataSource.getAccessToken().equals(null)
    }

    override suspend fun login(login: Login): Tokens {
        return remoteDataSource.login(login)
    }

    override suspend fun logout() {
        return remoteDataSource.logout()
    }

    override suspend fun me(): UserInfo {
        return remoteDataSource.me()
    }

    override suspend fun refresh(refreshToken: String): Tokens {
        return remoteDataSource.refresh(refreshToken)
    }

    override fun getAccessToken(): Flow<String?> {
        return localAuthDataSource.getAccessToken()
    }

    override suspend fun saveAccessToken(token: String) {
        localAuthDataSource.saveAccessToken(token)
    }

    override suspend fun removeAccessToken() {
        return localAuthDataSource.removeAccessToken()
    }

    override fun getRefreshToken(): Flow<String?> {
        return localAuthDataSource.getRefreshToken()
    }

    override suspend fun saveRefreshToken(token: String) {
        localAuthDataSource.saveRefreshToken(token)
    }

    override suspend fun removeRefreshToken() {
        return localAuthDataSource.removeRefreshToken()
    }
}