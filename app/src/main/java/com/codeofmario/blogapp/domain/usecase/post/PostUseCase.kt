package com.codeofmario.blogapp.domain.usecase.post

import com.codeofmario.blogapp.domain.entity.Pagination
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter

interface PostUseCase {
    suspend fun allPaginated(tableFilter: TableFilter): Pair<List<Post>, Pagination>
    suspend fun one(id: String): Post
}