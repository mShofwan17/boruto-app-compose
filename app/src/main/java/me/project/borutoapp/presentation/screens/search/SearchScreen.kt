package me.project.borutoapp.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import me.project.borutoapp.presentation.common.ListContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery
    val searchHeroesResult = viewModel.heroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChanged = { query ->
                    viewModel.updateSearchQuery(query).also {
                        if (query.length >= 3) viewModel.searchHeroes(query)
                    }
                },
                onSearchClicked = {
                    viewModel.searchHeroes(it)
                },
                onCloseClicked = { navHostController.popBackStack() }
            )
        }
    ) {
        ListContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            heroes = searchHeroesResult,
            controller = navHostController
        )
    }
}