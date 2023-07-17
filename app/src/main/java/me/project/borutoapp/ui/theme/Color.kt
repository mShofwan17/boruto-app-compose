package me.project.borutoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFFAFAFA)
val DarkGray = Color(0xFF2a2a2a)

val ColorScheme.welcomeScreenBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray
    else LightGray

val titleColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray
    else DarkGray

val descColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray.copy(alpha = 0.5f)
    else DarkGray.copy(alpha = 0.5f)


val activeIndicatiorColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Gray
    else Color.Blue

val inActiveIndicatiorColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White
    else Color.LightGray


val buttonBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black
    else Color.Blue


val topBarContentColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray
    else Color.White


val topBarBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black
    else Color.Blue