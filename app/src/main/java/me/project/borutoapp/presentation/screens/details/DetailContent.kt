package me.project.borutoapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import me.project.borutoapp.R
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.presentation.components.InfoBox
import me.project.borutoapp.presentation.components.OrderedList
import me.project.borutoapp.ui.theme.DP_PADDING_140
import me.project.borutoapp.ui.theme.DP_PADDING_16
import me.project.borutoapp.ui.theme.DP_PADDING_20
import me.project.borutoapp.ui.theme.DP_PADDING_32
import me.project.borutoapp.ui.theme.titleColor
import me.project.borutoapp.utils.Constant.Common.ABOUT_TEXT_MAX_LINES

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    navHostController: NavHostController,
    selectedHero: Hero?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = DP_PADDING_140,
        sheetContent = {
            selectedHero?.let { hero ->
                BottomSheetContent(selectedHero = hero)
            }
        }
    ) {

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