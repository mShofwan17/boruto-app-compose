package me.project.borutoapp.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.project.borutoapp.navigation.Screen
import me.project.borutoapp.presentation.common.ListContent
import me.project.borutoapp.ui.theme.statusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = statusBarColor
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeTopBar {
                navHostController.navigate(Screen.Search.route)
            }
        },
    ) { paddingValues ->
        ListContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            heroes = allHeroes,
            controller = navHostController
        )
    }
}

