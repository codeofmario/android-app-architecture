package com.codeofmario.blogapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeofmario.blogapp.ui.screens.login.LoginScreen
import com.codeofmario.blogapp.ui.screens.postdetail.PostDetailScreen
import com.codeofmario.blogapp.ui.screens.postsearch.PostSearchScreen
import com.codeofmario.blogapp.ui.screens.splash.SplashScreen
import com.codeofmario.blogapp.ui.screens.tabs.TabsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(
            Screen.Tabs.route,
        ) {
            val tab = when (it.arguments?.getString("tab")) {
                Tab.Posts.name -> Tab.Posts
                Tab.Profile.name -> Tab.Profile
                else -> Tab.Posts
            }

            TabsScreen(navController, tab)
        }

        composable(
            Screen.PostSearch.route,
        ) {
            PostSearchScreen(navController)
        }

        composable(
            Screen.PostDetail.route,
        ) {
            val id = it.arguments?.getString("id")

            PostDetailScreen(navController, id!!)
        }
    }
}
