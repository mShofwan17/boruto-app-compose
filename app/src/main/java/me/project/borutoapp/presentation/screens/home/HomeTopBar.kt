package me.project.borutoapp.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.project.borutoapp.R
import me.project.borutoapp.ui.theme.topBarContentColor
import me.project.borutoapp.ui.theme.topBarBackgroundColor

@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = topBarContentColor
            )
        },
        backgroundColor = topBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.cd_search_icon),
                    tint = topBarContentColor
                )
            }
        }
    )
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun Preview(){
    HomeTopBar {

    }
}