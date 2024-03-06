package com.codeofmario.blogapp.ui.screens.postdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.codeofmario.blogapp.R
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    navController: NavController,
    id: String,
    vm: PostDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        vm.getPost(id)
    }

    // States
    val scrollState = rememberScrollState()
    val post = vm.post.observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Post Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = "http://10.0.2.2:8000${post?.imageUrl}",
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )

                Spacer(Modifier.size(8.dp))

                Text(
                    text = "${post?.title}",
                    style = MaterialTheme.typography.headlineMedium,
                )

                Spacer(Modifier.size(8.dp))

                Text(
                    text = "${post?.body}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    )

}