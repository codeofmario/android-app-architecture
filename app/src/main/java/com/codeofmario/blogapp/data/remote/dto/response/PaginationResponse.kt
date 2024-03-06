package com.codeofmario.blogapp.data.remote.dto.response

data class PaginationResponse(
    val pageNumber: Int,
    val totalElements: Int,
    val totalPages: Int,
    val size: Int,
)