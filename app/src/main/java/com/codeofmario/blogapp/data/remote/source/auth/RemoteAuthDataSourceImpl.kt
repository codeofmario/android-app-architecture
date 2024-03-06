package com.codeofmario.blogapp.data.remote.source.auth

import com.codeofmario.blogapp.data.remote.dto.request.LoginRequest
import com.codeofmario.blogapp.data.remote.dto.response.TokensResponse
import com.codeofmario.blogapp.data.remote.dto.response.UserInfoResponse
import com.codeofmario.blogapp.data.remote.networking.AuthApi
import com.codeofmario.blogapp.domain.entity.Login
import com.codeofmario.blogapp.domain.entity.Tokens
import com.codeofmario.blogapp.domain.entity.UserInfo
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val api: AuthApi,
) : RemoteAuthDataSource {
    override suspend fun isAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun login(login: Login): Tokens {
        val dto = fromLoginToDto(login)
        return fromDtoToTokens(api.login(dto).data)
    }

    override suspend fun logout() {
        api.logout().runCatching {  }
    }

    override suspend fun me(): UserInfo {
        return fromDtoToUserInfo(api.me().data)
    }

    override suspend fun refresh(refreshToken: String): Tokens {
        return fromDtoToTokens(api.refresh("Bearer $refreshToken").data)
    }

    private fun fromLoginToDto(login: Login): LoginRequest {
        return LoginRequest(
            username = login.username,
            password = login.password,
        )
    }

    private fun fromDtoToUserInfo(dto: UserInfoResponse): UserInfo {
        return UserInfo(
            id = dto.id,
            username = dto.username,
            email = dto.email,
            avatarUrl = dto.avatarUrl,
        )
    }

    private fun fromDtoToTokens(dto: TokensResponse): Tokens {
        return Tokens(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken,
        )
    }
}