package com.pokeapi.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.pokeapi.domain.model.ResponseCatchReleaseRename

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
data class ResponseCatchReleaseRenameDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("status")
    var status: Boolean = false,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("nickname")
    var nickname: String? = null,
    @SerializedName("index")
    var index: Int? = null,
    @SerializedName("fibbonaci")
    var fibbonaci: Int? = null,
)
fun ResponseCatchReleaseRenameDto.toResponseCatchRelease(): ResponseCatchReleaseRename {
    return ResponseCatchReleaseRename(
        id = id,
        name = name,
        status = status,
        message = message,
        nickname = nickname,
        index = index,
        fibbonaci = fibbonaci,
    )
}