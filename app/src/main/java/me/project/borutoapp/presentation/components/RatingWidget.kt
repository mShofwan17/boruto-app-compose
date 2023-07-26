package me.project.borutoapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.project.borutoapp.R
import me.project.borutoapp.ui.theme.DP_PADDING_24
import me.project.borutoapp.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double
) {
    val startPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(startPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    FilledStar(
        starPath = starPath,
        starPathBounds = starPathBounds
    )

}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 1f
) {
    Canvas(modifier = Modifier.size(DP_PADDING_24)) {
        val canvasSize = this.size
        scale(scale = scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width/2f) - (pathWidth/1.7f)
            val top = (canvasSize.height/2f) - (pathHeight/1.7f)

            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }
        }

    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_NO)
private fun Preview() {
    RatingWidget(modifier = Modifier, rating = 1.0)
}