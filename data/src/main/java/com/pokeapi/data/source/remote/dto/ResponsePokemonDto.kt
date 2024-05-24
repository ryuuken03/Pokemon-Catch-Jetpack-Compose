package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.Pokemon
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.model.ResponsePokemonDetail

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class ResponsePokemonDto(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("limit")
    var limit: Int? = null,
    @SerializedName("max_page")
    var max_page: Int? = null,
    @SerializedName("next_page")
    var next_page: Int? = null,
    @SerializedName("previous_page")
    var previous_page: Int? = null,
    @SerializedName("results")
    var results: List<PokemonDto> = listOf(),
)
fun ResponsePokemonDto.toResponsePokemon(): ResponsePokemon {
    var arrayList = arrayListOf<Pokemon>()
    var list = results.map { it.toPokemon() }
    list.forEach {
        arrayList.add(it)
    }
    return ResponsePokemon(
        page = page,
        limit = limit,
        max_page = max_page,
        next_page = next_page,
        previous_page = previous_page,
        results = arrayList,
    )
}