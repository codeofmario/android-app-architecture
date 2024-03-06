package com.codeofmario.blogapp.ui.screens.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.usecase.post.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val useCase: PostUseCase
) : ViewModel() {
    private val _post = MutableLiveData<Post>(null)
    val post : LiveData<Post> = _post

    fun getPost(id: String) {
        viewModelScope.launch {
            try {
                _post.value = useCase.one(id)
            } catch (_: Exception) {

            }
        }
    }
}