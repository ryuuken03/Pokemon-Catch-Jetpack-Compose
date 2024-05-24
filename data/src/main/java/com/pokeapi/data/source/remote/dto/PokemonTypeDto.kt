package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.PokemonStat
import com.pokeapi.domain.model.PokemonType

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class PokemonTypeDto(
    @SerializedName("slot")
    var slot: Int? = null,
    @SerializedName("type")
    var type: ValueDto? = null,
)

fun PokemonTypeDto.toPokemonType(): PokemonType {
    return PokemonType(
        slot = slot,
        type = type?.toValueDef(),
    )
}