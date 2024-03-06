package com.codeofmario.blogapp.data.remote.dto.response

data class PaginatedResponse<T>(
    val status: Int,
    val data: T,
    val metadata: PaginationResponse
)