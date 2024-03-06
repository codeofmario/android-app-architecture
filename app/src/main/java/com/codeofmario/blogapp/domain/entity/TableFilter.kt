package com.codeofmario.blogapp.domain.entity

data class TableFilter(
    val search: String = "",
    val size: Int = 0,
    val page: Int = 10,
    val sortBy: String? = null,
    val sortDirection: String? = null,
    val all: Boolean = false,
)
