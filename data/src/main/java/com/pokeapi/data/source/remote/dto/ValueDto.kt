package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.Pokemon
import com.pokeapi.domain.model.ValueDef

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class ValueDto(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null,
)

fun ValueDto.toValueDef(): ValueDef {
    return ValueDef(
        name = name,
        url = url,
    )
}