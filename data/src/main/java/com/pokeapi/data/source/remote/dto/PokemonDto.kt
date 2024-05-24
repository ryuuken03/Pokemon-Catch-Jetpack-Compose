package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.Pokemon

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class PokemonDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("url")
    var url: String? = null,
)

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        image = image,
        url = url,
    )
}