package com.codeofmario.blogapp.ui.screens.postsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class PostSearchViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    // Pager
    val postPager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PostPagingSource(_search.value!!, repository)
    }.flow.cachedIn(viewModelScope)

    private val _search = MutableLiveData("")
    val search: LiveData<String> = _search
    fun onSearchChanged(search: String) {
        _search.value = search
    }
}