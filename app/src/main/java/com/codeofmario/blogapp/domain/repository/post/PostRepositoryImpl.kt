package com.codeofmario.blogapp.domain.repository.post

import com.codeofmario.blogapp.data.remote.source.post.RemotePostDataSource
import com.codeofmario.blogapp.domain.entity.Pagination
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemotePostDataSource,
) : PostRepository {
    override suspend fun allPaginated(tableFilter: TableFilter): Pair<List<Post>, Pagination> {
        return remoteDataSource.allPaginated(tableFilter)
    }

    override suspend fun one(id: String): Post {
        return remoteDataSource.one(id)
    }

}