package com.codeofmario.blogapp.data.remote.dto.request

data class TableFilterRequest(
    val search: String = "",
    val size: Int = 0,
    val page: Int = 10,
    val sortBy: String?,
    val sortDirection: String?,
    val all: Boolean = false,
)
