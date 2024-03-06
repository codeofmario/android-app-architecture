package com.codeofmario.blogapp.ui.screens.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.codeofmario.blogapp.R
import com.codeofmario.blogapp.Screen
import com.codeofmario.blogapp.extension.ManageState
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun PostsScreen(
    navController: NavController,
    vm: PostsViewModel = hiltViewModel()
) {
    // List content
    val posts = vm.postPager.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.BlogApp)) },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.PostSearch.getRoute()
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ManageState(posts.loadState.prepend)
            ManageState(posts.loadState.refresh)

            itemsIndexed(posts, key = { _, item -> item.id }) { _, item ->
                item?.let {
                    Column(
                        modifier = Modifier
                            .combinedClickable(
                                onClick = {
                                    navController.navigate(
                                        Screen.PostDetail.getRoute(
                                            item.id
                                        )
                                    )
                                },
                            )
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(5.dp))

                    ) {
                        AsyncImage(
                            model = "https://10.0.2.2:8000${item.imageUrl}",
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(R.drawable.placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }

            ManageState(posts.loadState.append)
        }
    }
}