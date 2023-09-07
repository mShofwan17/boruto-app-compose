package me.project.borutoapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero = viewModel.selectedHero.value

}