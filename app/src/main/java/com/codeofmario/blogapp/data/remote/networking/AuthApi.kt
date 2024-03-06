package com.codeofmario.blogapp.data.remote.networking

import com.codeofmario.blogapp.data.remote.dto.request.LoginRequest
import com.codeofmario.blogapp.data.remote.dto.response.Response
import com.codeofmario.blogapp.data.remote.dto.response.TokensResponse
import com.codeofmario.blogapp.data.remote.dto.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/signin")
    suspend fun login(@Body dto: LoginRequest): Response<TokensResponse>

    @POST("auth/logout")
    suspend fun logout(): retrofit2.Response<Unit>

    @GET("auth/me")
    suspend fun me(): Response<UserInfoResponse>

    @POST("auth/refresh")
    suspend fun refresh(@Header("Authorization") bearer: String): Response<TokensResponse>
}