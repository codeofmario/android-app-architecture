package com.codeofmario.blogapp.domain.usecase.post

import com.codeofmario.blogapp.domain.entity.Pagination
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter
import com.codeofmario.blogapp.domain.repository.post.PostRepository
import javax.inject.Inject

class PostUseCaseImpl @Inject constructor(
    private val repository: PostRepository
) : PostUseCase {
    override suspend fun allPaginated(tableFilter: TableFilter): Pair<List<Post>, Pagination> {
        return repository.allPaginated(tableFilter)
    }

    override suspend fun one(id: String): Post {
        return repository.one(id)
    }

}