package com.codeofmario.blogapp.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.codeofmario.blogapp.R
import com.codeofmario.blogapp.Screen
import com.codeofmario.blogapp.ui.theme.BlogAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    vm: ProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        vm.getUserInfo()
    }

    // states
    val userInfo = vm.userInfo.observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
            )
        },
        content =  { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = "http://10.0.2.2:8000${userInfo?.avatarUrl}",
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(CircleShape)
                )

                Spacer(Modifier.size(32.dp))

                Row() {
                    Text(text = "Id:", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                    Text(text = "${userInfo?.id}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                }

                Spacer(Modifier.size(8.dp))

                Row() {
                    Text(text = "Username:", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                    Text(text = "${userInfo?.username}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                }

                Spacer(Modifier.size(8.dp))

                Row() {
                    Text(text = "Email:", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                    Text(text = "${userInfo?.email}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                }

                Spacer(Modifier.size(32.dp))

                Button(
                    onClick = {
                        vm.logout {
                            if (it) {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.Tabs.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    },
                    content = {
                        Text(text = "Logout", color = Color.White)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            }

        }
    )

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()

    BlogAppTheme {
        ProfileScreen(navController)
    }
}