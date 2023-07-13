package me.project.borutoapp.presentation.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import me.project.borutoapp.domain.models.OnBoardingPage
import me.project.borutoapp.ui.theme.welcomeScreenBackgroundColor

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    Welcome(pages = pages)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Welcome(pages: List<OnBoardingPage>) {
    val pagerState = rememberPagerState { pages.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.welcomeScreenBackgroundColor)
    ) {
       HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {page ->
            PagerScreen(onBoardingPage = pages[page])
        }
    }
}

@Composable
private fun PagerScreen(onBoardingPage: OnBoardingPage){

}