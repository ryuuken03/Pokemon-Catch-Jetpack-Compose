package com.pokeapi.presentation.navigation.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
sealed class BottomBar(
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconFocused: Int
)