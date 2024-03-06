package com.codeofmario.blogapp.data.remote.dto.response

data class Response<T>(
    val status: Int,
    val data: T
)