package com.pokeapi.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pokeapi.presentation.navigation.screen.BottomBarScreen
import com.pokeapi.presentation.navigation.screen.GeneralScreen
import com.pokeapi.presentation.screen.detailpokemon.DetailPokemonScreen
import com.pokeapi.presentation.screen.listpokemon.ListPokemonScreen
import com.pokeapi.presentation.screen.mypokemon.MyPokemonScreen

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ListPokemon.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = BottomBarScreen.ListPokemon.route) {
            ListPokemonScreen(
                navigateToDetail = { id,title ->
                    navController.navigate(
                        GeneralScreen.DetailPokemonScreen
                            .sendData(id,title)
                    )
                }
            )
        }
        composable(route = BottomBarScreen.MyPokemon.route) {
            MyPokemonScreen(
                navigateToDetail = { id,title ->
                    navController.navigate(
                        GeneralScreen.DetailPokemonScreen
                            .sendData(id,title)
                    )
                }
            )
        }
        composable(route = GeneralScreen.DetailPokemonScreen.route){
            var id = it.arguments?.getString("id") ?: "0"
            var title = it.arguments?.getString("title") ?: "-"
            DetailPokemonScreen(
                id = id,
                title = title,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}