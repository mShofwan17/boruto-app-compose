package me.project.borutoapp.presentation.screens.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPagerIndicator
import me.project.borutoapp.R
import me.project.borutoapp.domain.models.OnBoardingPage
import me.project.borutoapp.ui.theme.DP_PADDING_10
import me.project.borutoapp.ui.theme.DP_PADDING_12
import me.project.borutoapp.ui.theme.DP_PADDING_16
import me.project.borutoapp.ui.theme.DP_PADDING_40
import me.project.borutoapp.ui.theme.DP_PADDING_8
import me.project.borutoapp.ui.theme.activeIndicatiorColor
import me.project.borutoapp.ui.theme.buttonBackgroundColor
import me.project.borutoapp.ui.theme.descColor
import me.project.borutoapp.ui.theme.inActiveIndicatiorColor
import me.project.borutoapp.ui.theme.titleColor
import me.project.borutoapp.ui.theme.welcomeScreenBackgroundColor

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    Welcome(pages = pages) {

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Welcome(
    pages: List<OnBoardingPage>,
    onFinishClick: () -> Unit
) {
    val pagerState = rememberPagerState { pages.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.welcomeScreenBackgroundColor),
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = pages.size,
                activeColor = activeIndicatiorColor,
                inactiveColor = inActiveIndicatiorColor,
                indicatorWidth = DP_PADDING_12,
                spacing = DP_PADDING_8
            )
        }

        FinishButton(
            Modifier.weight(2f),
            pagerState = pagerState,
            onClick = onFinishClick
        )

    }
}

@Composable
private fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(id = R.string.cd_onboarding_image)
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = onBoardingPage.title,
            color = titleColor,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DP_PADDING_40)
                .padding(top = DP_PADDING_10),
            text = onBoardingPage.desc,
            color = descColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = DP_PADDING_40),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }

    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun Preview() {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    Welcome(pages = pages) {}
}



