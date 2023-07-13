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
        desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec justo enim, condimentum at felis ut, pulvinar pulvinar sem. Duis ut consequat quam, sit amet scelerisque tellus. "
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Menjelajah",
        desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec justo enim, condimentum at felis ut, pulvinar pulvinar sem. Duis ut consequat quam, sit amet scelerisque tellus. "
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Kekuatan",
        desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec justo enim, condimentum at felis ut, pulvinar pulvinar sem. Duis ut consequat quam, sit amet scelerisque tellus. "
    )
}
