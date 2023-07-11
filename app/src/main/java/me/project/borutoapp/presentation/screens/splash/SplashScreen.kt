package me.project.borutoapp.presentation.screens.splash


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import me.project.borutoapp.R
import me.project.borutoapp.ui.theme.Purple40
import me.project.borutoapp.ui.theme.Purple80

@Composable
fun SplashScreen(navHostController: NavHostController) {
    Splash()
}

@Composable
fun Splash() {
    val backgroundColor = if (isSystemInDarkTheme()) listOf(Color.Black, Color.DarkGray)
    else listOf(Color.Blue, Color.Cyan)

    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(backgroundColor))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.app_logo),
            //modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    Splash()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun DarkReview() {
    Splash()
}