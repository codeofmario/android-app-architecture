package com.codeofmario.blogapp.domain.entity

data class UserInfo(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String?
)
