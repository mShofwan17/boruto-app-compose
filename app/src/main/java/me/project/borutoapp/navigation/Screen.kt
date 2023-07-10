package me.project.borutoapp.navigation

import me.project.borutoapp.utils.Constant.ID

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen/{$ID}") {
        fun passId(id: Int): String {
            return "detail_screen/$id"
        }
    }

    object Search : Screen("search_screen")
}
