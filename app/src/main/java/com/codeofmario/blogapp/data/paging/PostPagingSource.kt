package com.codeofmario.blogapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter
import com.codeofmario.blogapp.domain.repository.post.PostRepository
import com.codeofmario.blogapp.extension.nextKey
import com.codeofmario.blogapp.extension.prevKey

class PostPagingSource(
    private val search: String = "",
    private val repository: PostRepository
) : PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPageNumber = params.key ?: 0
            val tableFilter = TableFilter(
                search,
                params.loadSize,
                nextPageNumber
            )
            val response = repository.allPaginated(tableFilter)
            LoadResult.Page(
                data = response.first,
                prevKey = params.prevKey(),
                nextKey = params.nextKey(response.second.totalElements)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
}