package com.codeofmario.blogapp.domain.entity

data class Post(
    val id: String,
    val title: String,
    val body: String,
    val imageUrl: String?,
)
