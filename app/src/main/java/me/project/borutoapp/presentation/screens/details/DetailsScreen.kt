package me.project.borutoapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by viewModel.selectedHero.collectAsState()
    DetailContent(navHostController = navHostController, selectedHero = selectedHero)
}