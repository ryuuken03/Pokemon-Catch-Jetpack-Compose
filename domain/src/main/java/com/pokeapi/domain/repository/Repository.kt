package com.pokeapi.domain.repository

import com.pokeapi.domain.model.ResponseCatchReleaseRename
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.model.ResponsePokemonDetail
import kotlinx.coroutines.flow.Flow

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
interface Repository {
    suspend fun getListPokemon(
        page: Int,
        limit: Int
    ): Flow<ResponsePokemon>

    suspend fun getDetailPokemon(
        id: Int,
    ): Flow<ResponsePokemonDetail>

    suspend fun getCatchPokemon(
        id: Int,
        name: String,
    ): Flow<ResponseCatchReleaseRename>


    suspend fun getReleasePokemon(
        id: Int,
        name: String,
    ): Flow<ResponseCatchReleaseRename>

    suspend fun getRenamePokemon(
        id: Int,
        name: String,
        nickname: String,
        index: Int,
    ): Flow<ResponseCatchReleaseRename>


}