package com.pokeapi.domain.repository

import com.pokeapi.domain.model.DBMyPokemon
import kotlinx.coroutines.flow.Flow

/***
 * Created By Mohammad Toriq on 09/01/2024
 */
interface DatabaseRepository {
    suspend fun getListMyPokemon(): Flow<MutableList<DBMyPokemon>>
    suspend fun getMyPokemon(id : Int): Flow<DBMyPokemon>
    suspend fun insertMyPokemon(
        id: Int,
        name: String,
        image: String,
        nickname: String,
        index: Int,
        fibbonaci: Int)
    suspend fun deleteMyPokemon(id : Int)
}