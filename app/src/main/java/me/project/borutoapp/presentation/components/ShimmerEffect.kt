package me.project.borutoapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import me.project.borutoapp.ui.theme.DP_PADDING_10
import me.project.borutoapp.ui.theme.DP_PADDING_16
import me.project.borutoapp.ui.theme.DP_PADDING_4
import me.project.borutoapp.ui.theme.DP_PADDING_6
import me.project.borutoapp.ui.theme.DP_PADDING_8
import me.project.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import me.project.borutoapp.ui.theme.SHIMMER_ABOUT_PLACEHOLDER_HEIGHT
import me.project.borutoapp.ui.theme.SHIMMER_NAME_PLACEHOLDER_HEIGHT
import me.project.borutoapp.ui.theme.SHIMMER_RATING_PLACEHOLDER_HEIGHT
import me.project.borutoapp.ui.theme.ShimmerDarkGray
import me.project.borutoapp.ui.theme.ShimmerLightGray
import me.project.borutoapp.ui.theme.ShimmerMediumGray


@Composable
fun ShimmerEffect() {

}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "shimmer"
    )

    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(
    alpha: Float
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT),
        color = if (isSystemInDarkTheme()) Color.Black
        else ShimmerLightGray,
        shape = RoundedCornerShape(DP_PADDING_16)
    ) {
        Column(
            modifier = Modifier.padding(all = DP_PADDING_16),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .fillMaxWidth(0.5f)
                    .height(SHIMMER_NAME_PLACEHOLDER_HEIGHT),
                color = if (isSystemInDarkTheme()) ShimmerDarkGray
                else ShimmerMediumGray,
                shape = RoundedCornerShape(DP_PADDING_8)
            ) {}

            Spacer(modifier = Modifier.padding(all = DP_PADDING_10))

            repeat(3) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .fillMaxWidth()
                        .height(SHIMMER_ABOUT_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray
                    else ShimmerMediumGray,
                    shape = RoundedCornerShape(DP_PADDING_8)
                ) {}
                Spacer(modifier = Modifier.padding(all = DP_PADDING_6))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = DP_PADDING_8)
            ) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .size(SHIMMER_RATING_PLACEHOLDER_HEIGHT),
                        color = if (isSystemInDarkTheme()) ShimmerDarkGray
                        else ShimmerMediumGray,
                        shape = RoundedCornerShape(DP_PADDING_4)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = DP_PADDING_8))
                }
            }
        }
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerPreview() {
    AnimatedShimmerItem()
}