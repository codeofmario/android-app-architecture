package com.codeofmario.blogapp.data.remote.dto.response

data class UserInfoResponse(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String?,
)
