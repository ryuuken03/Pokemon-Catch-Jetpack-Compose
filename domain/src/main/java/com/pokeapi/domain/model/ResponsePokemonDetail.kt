package com.pokeapi.domain.model

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class ResponsePokemonDetail(
    var id: Int? = null,
    var name: String? = null,
    var image: String? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var types: List<PokemonType> = listOf(),
    var stats: List<PokemonStat> = listOf(),
)