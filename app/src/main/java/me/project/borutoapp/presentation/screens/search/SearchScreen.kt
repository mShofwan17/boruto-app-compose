package me.project.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            SearchTopBar(
                text = "",
                onTextChanged = {},
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
    ) {

    }
}