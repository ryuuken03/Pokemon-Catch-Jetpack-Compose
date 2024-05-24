package com.pokeapi.presentation.navigation.screen

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
sealed class GeneralScreen(val route: String) {

    object DetailPokemonScreen: GeneralScreen("detail_pokemon/{id}/{title}"){
        fun sendData(id: String,title:String) = "detail_pokemon/$id/$title"
    }

}