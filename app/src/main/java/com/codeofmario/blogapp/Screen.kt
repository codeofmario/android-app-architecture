package com.codeofmario.blogapp

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash")
    object Login : Screen(route = "auth/login")
    object Tabs : Screen(route = "tabs/{tab}") {
        fun getRoute(tab: Tab) = "tabs/${tab.name}"
    }
    object PostSearch : Screen(route = "post/search?from={from}") {
        fun getRoute(from: String? = null): String {
            val query = if (from != null) "?from=$from" else ""
            return "post/search$query"
        }
    }
    object PostDetail : Screen(route = "post/{id}") {
        fun getRoute(id: String) = "post/$id"
    }
}