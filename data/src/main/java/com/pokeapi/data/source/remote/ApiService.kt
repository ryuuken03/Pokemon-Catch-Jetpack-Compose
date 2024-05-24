package com.pokeapi.data.source.remote

import com.pokeapi.data.source.remote.dto.ResponseCatchReleaseRenameDto
import com.pokeapi.data.source.remote.dto.ResponsePokemonDetailDto
import com.pokeapi.data.source.remote.dto.ResponsePokemonDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
interface ApiService {
    companion object {
        const val BASE_URL = "http://192.168.191.58:8000/api/"
    }

    @GET("all")
    suspend fun getListPokemon(
        @Query(value = "page", encoded = true) page: Int,
        @Query(value = "limit", encoded = true) limit: Int,
    ): ResponsePokemonDto

    @GET("detail")
    suspend fun getDetailPokemon(
        @Query(value = "id", encoded = true) id: Int,
    ): ResponsePokemonDetailDto

    @GET("catch")
    suspend fun getCatchPokemon(
        @Query(value = "id", encoded = true) id: Int,
        @Query(value = "name", encoded = true) name: String,
    ): ResponseCatchReleaseRenameDto

    @GET("release")
    suspend fun getReleasePokemon(
        @Query(value = "id", encoded = true) id: Int,
        @Query(value = "name", encoded = true) name: String,
    ): ResponseCatchReleaseRenameDto

    @GET("rename")
    suspend fun getRenamePokemon(
        @Query(value = "id", encoded = true) id: Int,
        @Query(value = "name", encoded = true) name: String,
        @Query(value = "nickname", encoded = true) nickname: String,
        @Query(value = "index", encoded = true) index: Int,
    ): ResponseCatchReleaseRenameDto
}