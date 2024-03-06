package com.codeofmario.blogapp.ui.screens.tabs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codeofmario.blogapp.Screen
import com.codeofmario.blogapp.Tab
import com.codeofmario.blogapp.ui.screens.posts.PostsScreen
import com.codeofmario.blogapp.ui.screens.profile.ProfileScreen
import com.codeofmario.blogapp.ui.theme.BlogAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsScreen(
    navController: NavController,
    tab: Tab,
) {
    Scaffold(
        bottomBar = { BottomBar(navController, tab) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (tab) {
                Tab.Posts -> PostsScreen(navController)
                Tab.Profile -> ProfileScreen(navController)
            }
        }
    }

}


@Composable
fun BottomBar(navController: NavController, tab: Tab) {
    val items = listOf(
        Tab.Posts,
        Tab.Profile,
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.onPrimary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = if (item == tab) MaterialTheme.colorScheme.primary else Color.LightGray
                    )
                },
                selected = item == tab,
                selectedContentColor = Color.LightGray,
                unselectedContentColor = Color.White,
                onClick = {
                    navController.navigate(Screen.Tabs.getRoute(item)) {
                        popUpTo(Screen.Tabs.route)
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabsScreenPreview() {
    val navController = rememberNavController()

    BlogAppTheme {
        TabsScreen(navController, Tab.Posts)
    }
}