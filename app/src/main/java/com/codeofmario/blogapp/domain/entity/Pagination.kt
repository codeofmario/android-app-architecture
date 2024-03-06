package com.codeofmario.blogapp.domain.entity

data class Pagination(
    val pageNumber: Int,
    val totalElements: Int,
    val totalPages: Int,
    val size: Int,
)
