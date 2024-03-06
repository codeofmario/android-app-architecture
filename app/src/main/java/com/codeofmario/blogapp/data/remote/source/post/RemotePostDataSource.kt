package com.codeofmario.blogapp.data.remote.source.post

import com.codeofmario.blogapp.domain.entity.Pagination
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter

interface RemotePostDataSource {
    suspend fun allPaginated(filter: TableFilter): Pair<List<Post>, Pagination>
    suspend fun one(id: String): Post
}