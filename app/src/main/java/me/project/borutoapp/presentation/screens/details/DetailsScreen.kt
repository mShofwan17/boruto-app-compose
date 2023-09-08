package me.project.borutoapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import me.project.borutoapp.utils.Constant
import me.project.borutoapp.utils.PaletteGenerator

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by viewModel.selectedHero.collectAsState()
    val colorPalette by viewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailContent(
            navHostController = navHostController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else {
        viewModel.generateColorPalette()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = PaletteGenerator.convertImageUrlToBitmap(
                        imageUrl = "${Constant.NetworkConst.BASE_URL}${selectedHero?.image}",
                        context = context
                    )

                    if (bitmap != null) viewModel.setColorPalette(
                        color = PaletteGenerator.extractColorsFromBitmap(
                            bitmap
                        )
                    )
                }
            }
        }
    }

}