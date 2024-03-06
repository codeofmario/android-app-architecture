package com.codeofmario.blogapp.domain.repository.post

import com.codeofmario.blogapp.domain.entity.Pagination
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter

interface PostRepository {
    suspend fun allPaginated(tableFilter: TableFilter): Pair<List<Post>, Pagination>
    suspend fun one(id: String): Post
}