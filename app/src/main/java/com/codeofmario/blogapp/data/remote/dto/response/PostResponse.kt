package com.codeofmario.blogapp.data.remote.dto.response

data class PostResponse(
    val id: String,
    val title: String,
    val body: String,
    val imageUrl: String?,
    val createdBy: UserInfoResponse
)
