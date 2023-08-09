package me.project.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.project.borutoapp.presentation.screens.home.HomeScreen
import me.project.borutoapp.presentation.screens.search.SearchScreen
import me.project.borutoapp.presentation.screens.splash.SplashScreen
import me.project.borutoapp.presentation.screens.welcome.WelcomeScreen
import me.project.borutoapp.utils.Constant

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navHostController = navController)
        }

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navHostController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                navController
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(name = Constant.ID) {
                type = NavType.IntType
            })
        ) {

        }

        composable(route = Screen.Search.route) {
            SearchScreen(navHostController = navController)
        }
    }
}