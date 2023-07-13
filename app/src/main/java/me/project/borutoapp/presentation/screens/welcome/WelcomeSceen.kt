package me.project.borutoapp.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
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

@Composable
private fun Welcome(pages: List<OnBoardingPage>) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            state = pagerState,
            count = pages.size,
            verticalAlignment = Alignment.Top
        ) {page ->
            PagerScreen(onBoardingPage = pages[page])
        }
    }
}

@Composable
private fun PagerScreen(onBoardingPage: OnBoardingPage){

}