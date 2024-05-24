package com.pokeapi.presentation.navigation.screen
import com.pokeapi.presentation.R

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
sealed class BottomBarScreen(val route: String) {
    object ListPokemon : BottomBar(
        route = "list",
        titleResId = R.string.menu_list,
        icon = R.drawable.world_24,
        iconFocused = R.drawable.world_24
    )

    object MyPokemon : BottomBar(
        route = "my_pokemon",
        titleResId = R.string.menu_my_pokemon,
        icon = R.drawable.pokeball_24,
        iconFocused = R.drawable.pokeball_24
    )
}