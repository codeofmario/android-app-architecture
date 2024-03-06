package com.codeofmario.blogapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Tab(
    val name: String,
    val label: String,
    val icon: ImageVector
) {
    object Posts : Tab(
        name = "posts",
        label = "Posts",
        icon = Icons.Default.MenuBook
    )

    object Profile : Tab(
        name = "profile",
        label = "Profile",
        icon = Icons.Default.Person
    )
}