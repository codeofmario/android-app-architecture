package com.codeofmario.blogapp.data.remote.dto.response

data class TokensResponse(
    val accessToken: String,
    val refreshToken: String,
)
