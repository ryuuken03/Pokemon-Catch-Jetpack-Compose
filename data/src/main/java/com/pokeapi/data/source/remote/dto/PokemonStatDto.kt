package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.PokemonStat
import com.pokeapi.domain.model.ValueDef

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class PokemonStatDto(
    @SerializedName("base_stat")
    var base_stat: Int? = null,
    @SerializedName("effort")
    var effort: Int? = null,
    @SerializedName("stat")
    var stat: ValueDto? = null,
)

fun PokemonStatDto.toPokemonStat(): PokemonStat {
    return PokemonStat(
        base_stat = base_stat,
        effort = effort,
        stat = stat?.toValueDef(),
    )
}