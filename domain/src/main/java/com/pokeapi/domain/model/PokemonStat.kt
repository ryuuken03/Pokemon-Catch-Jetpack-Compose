package com.pokeapi.domain.model

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class PokemonStat(
    var base_stat: Int? = null,
    var effort: Int? = null,
    var stat: ValueDef? = null,
)