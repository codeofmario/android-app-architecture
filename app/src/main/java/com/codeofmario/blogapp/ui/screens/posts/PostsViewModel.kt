package com.codeofmario.blogapp.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.codeofmario.blogapp.data.paging.PostPagingSource
import com.codeofmario.blogapp.domain.repository.post.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val PAGE_SIZE = 10

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    // Pager
    val postPager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PostPagingSource("", repository)
    }.flow.cachedIn(viewModelScope)
}