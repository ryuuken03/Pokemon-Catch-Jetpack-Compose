package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.PokemonType
import com.pokeapi.domain.model.ResponsePokemonDetail

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class ResponsePokemonDetailDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("weight")
    var weight: Int? = null,
    @SerializedName("types")
    var types: List<PokemonTypeDto> = listOf(),
    @SerializedName("stats")
    var stats: List<PokemonStatDto> = listOf(),
)
fun ResponsePokemonDetailDto.toResponsePokemonDetail(): ResponsePokemonDetail {
    return ResponsePokemonDetail(
        id = id,
        name = name,
        image = image,
        height = height,
        weight = weight,
        types = types.map { it.toPokemonType() }.toList(),
        stats = stats.map { it.toPokemonStat() }.toList(),
    )
}