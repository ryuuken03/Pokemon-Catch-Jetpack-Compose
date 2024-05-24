package com.pokeapi.domain.model

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class ResponsePokemon(
    var page: Int? = null,
    var limit: Int? = null,
    var max_page: Int? = null,
    var next_page: Int? = null,
    var previous_page: Int? = null,
    var results: ArrayList<Pokemon> = arrayListOf(),
)