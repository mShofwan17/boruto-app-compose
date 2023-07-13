package me.project.borutoapp.domain.models

import androidx.annotation.DrawableRes
import me.project.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val desc: String
) {
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Selamat Datang",
        desc = "Lorem Impusmasklndahdjkashdjkhajkdhkjhakdhjakhdjkashdjasdhkahd"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Menjelajah",
        desc = "Lorem Impusmasklndahdjkashdjkhajkdhkjhakdhjakhdjkashdjasdhkahd"
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Kekuatan",
        desc = "Lorem Impusmasklndahdjkashdjkhajkdhkjhakdhjakhdjkashdjasdhkahd"
    )
}
