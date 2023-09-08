package me.project.borutoapp.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.project.borutoapp.R
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.presentation.components.InfoBox
import me.project.borutoapp.presentation.components.OrderedList
import me.project.borutoapp.ui.theme.DP_PADDING_140
import me.project.borutoapp.ui.theme.DP_PADDING_16
import me.project.borutoapp.ui.theme.DP_PADDING_20
import me.project.borutoapp.ui.theme.DP_PADDING_32
import me.project.borutoapp.ui.theme.DP_PADDING_40
import me.project.borutoapp.ui.theme.DP_PADDING_8
import me.project.borutoapp.ui.theme.titleColor
import me.project.borutoapp.utils.Constant
import me.project.borutoapp.utils.Constant.Common.ABOUT_TEXT_MAX_LINES
import me.project.borutoapp.utils.Constant.Common.MIN_BACKGROUND_FRACTION

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    navHostController: NavHostController,
    selectedHero: Hero?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 = selectedHero) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnime by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f) DP_PADDING_32
        else 0.dp,
        label = stringResource(R.string.radius_bottom_sheet)
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnime,
            topEnd = radiusAnime
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = DP_PADDING_140,
        sheetContent = {
            selectedHero?.let { hero ->
                BottomSheetContent(
                    selectedHero = hero,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant)),
                )
            }
        }
    ) {
        selectedHero?.image?.let { image ->
            BackgroundContent(
                imageHero = image,
                imageFraction = currentSheetFraction,
                backgroundColor = Color(parseColor(darkVibrant))
            ) {
                navHostController.popBackStack()
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(DP_PADDING_20)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = DP_PADDING_20),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(DP_PADDING_32)
                    .weight(2f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(
                    R.string.logo
                ),
                tint = contentColor
            )

            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = DP_PADDING_16),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(
                    id = R.drawable.bolt
                ),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(
                    id = R.drawable.calendar
                ),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = "Month",
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(
                    id = R.drawable.cake
                ),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = DP_PADDING_16),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )

        }
    }
}

@Composable
fun BackgroundContent(
    imageHero: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "${Constant.NetworkConst.BASE_URL}$imageHero"
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.placeholder)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_FRACTION)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(R.string.cd_hero_image),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(DP_PADDING_8),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(DP_PADDING_32),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.cd_img_close),
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    val dummy = Hero(
        id = 1,
        name = "Sasuke",
        image = "/images/sasuke.jpg",
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
    BottomSheetContent(selectedHero = dummy)
}