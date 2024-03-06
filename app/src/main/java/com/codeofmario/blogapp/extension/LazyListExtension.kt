package com.codeofmario.blogapp.extension

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

fun LazyListScope.ManageState(state: LoadState) {
    when (state) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        is LoadState.Error -> {
            item {
                Text(
                    text = state.error.message ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}