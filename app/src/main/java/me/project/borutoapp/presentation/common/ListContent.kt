package me.project.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import me.project.borutoapp.R
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.navigation.Screen
import me.project.borutoapp.presentation.components.RatingWidget
import me.project.borutoapp.ui.theme.DP_PADDING_10
import me.project.borutoapp.ui.theme.DP_PADDING_12
import me.project.borutoapp.ui.theme.DP_PADDING_16
import me.project.borutoapp.ui.theme.DP_PADDING_24
import me.project.borutoapp.ui.theme.DP_PADDING_8
import me.project.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import me.project.borutoapp.ui.theme.topBarContentColor
import me.project.borutoapp.utils.Constant.NetworkConst.BASE_URL

@Composable
fun ListContent(
    modifier: Modifier,
    heroes: LazyPagingItems<Hero>,
    controller: NavHostController
) {
    LazyColumn(
        contentPadding = PaddingValues(all = DP_PADDING_10),
        verticalArrangement = Arrangement.spacedBy(DP_PADDING_10)
    ){
        items(
            count = heroes.itemCount,
            key = heroes.itemKey { it.id },
        ){index ->
            val item = heroes[index]
            item?.let {
                HeroItem (item = it, controller = controller)
            }
        }
    }
}

@Composable
fun HeroItem(
    item: Hero,
    controller: NavHostController
) {
    val painter = rememberAsyncImagePainter(
        model =  "$BASE_URL${item.image}",
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.placeholder)
    )

    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                controller.navigate(Screen.Detail.passId(id = item.id))
            },
        contentAlignment = Alignment.BottomStart,
    ) {
        Surface(
            shape = RoundedCornerShape(
               size = DP_PADDING_24
            )
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(id = R.string.cd_hero_image),
                contentScale = ContentScale.Crop
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = DP_PADDING_24,
                bottomEnd = DP_PADDING_24
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = DP_PADDING_16)
            ) {
                Text(
                    text = item.name,
                    color = topBarContentColor,
                    fontSize = androidx.compose.material3.MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = androidx.compose.material3.MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier
                        .padding(top = DP_PADDING_12),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = DP_PADDING_12),
                        rating = item.rating
                    )

                    Text(
                        text = "(${item.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun Preview() {
    val item = Hero(
        id = 1,
        name = "Sasuke",
        image = "https://media-assets-ggwp.s3.ap-southeast-1.amazonaws.com/2021/11/final-form-susanoo-sasuke-640x360.jpg",
        about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
        rating = 5.0,
        power = 98,
        month = "July",
        day = "23rd",
        family = listOf(
            "Fugaku", "Mikoto", "Itachi", "Sarada", "Sakura"
        ),
        abilities = listOf(
            "Sharingan", "Rinnegan", "Sussano", "Amateratsu", "Intelligence"
        ),
        natureTypes = listOf(
            "Lightning", "Fire", "Wind", "Earth", "Water"
        )
    )

    HeroItem(
        item = item,
        controller = rememberNavController()
    )
}