package com.codeofmario.blogapp.ui.screens.postsearch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.codeofmario.blogapp.R
import com.codeofmario.blogapp.Screen
import com.codeofmario.blogapp.extension.ManageState

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun PostSearchScreen(
    navController: NavController,
    vm: PostSearchViewModel = hiltViewModel()
) {
    // List content
    val posts = vm.postPager.collectAsLazyPagingItems()

    val search = vm.search.observeAsState("").value

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = search,
                        onValueChange = {
                            vm.onSearchChanged(it)
                            posts.refresh()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        placeholder = {
                            Text(text = "Search")
                        },
                        trailingIcon = {
                            if (search != "") {
                                IconButton(onClick = {
                                    vm.onSearchChanged("")
                                    posts.refresh()
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Clean"
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                        ),
                        singleLine = true,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
    ) { paddingValues ->

        Column {

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
                                .border(
                                    BorderStroke(1.dp, Color.LightGray),
                                    RoundedCornerShape(5.dp)
                                )
                        ) {
                            AsyncImage(
                                model = "http://10.0.2.2:8000${item.imageUrl}",
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

}