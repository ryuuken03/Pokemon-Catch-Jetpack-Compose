package com.pokeapi.data.repository

import android.util.Log
import com.pokeapi.data.source.remote.ApiService
import com.pokeapi.data.source.remote.dto.toResponseCatchRelease
import com.pokeapi.data.source.remote.dto.toResponsePokemon
import com.pokeapi.data.source.remote.dto.toResponsePokemonDetail
import com.pokeapi.domain.model.ResponseCatchReleaseRename
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.model.ResponsePokemonDetail
import com.pokeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository  {
    override suspend fun getListPokemon(page: Int, limit: Int): Flow<ResponsePokemon> {
        return flow {
            var data = apiService.getListPokemon(page,limit)
            emit(data.toResponsePokemon())
        }
    }

    override suspend fun getDetailPokemon(id: Int): Flow<ResponsePokemonDetail> {
        return flow {
            var data = apiService.getDetailPokemon(id)
            emit(data.toResponsePokemonDetail())
        }
    }

    override suspend fun getCatchPokemon(id: Int, name: String): Flow<ResponseCatchReleaseRename> {
        return flow {
            var data = apiService.getCatchPokemon(id,name)
            emit(data.toResponseCatchRelease())
        }
    }

    override suspend fun getReleasePokemon(
        id: Int,
        name: String
    ): Flow<ResponseCatchReleaseRename> {
        return flow {
            var data = apiService.getReleasePokemon(id,name)
            emit(data.toResponseCatchRelease())
        }
    }

    override suspend fun getRenamePokemon(
        id: Int,
        name: String,
        nickname: String,
        index: Int
    ): Flow<ResponseCatchReleaseRename> {
        return flow {
            var data = apiService.getRenamePokemon(id= id,name = name, index = index, nickname = nickname)
            emit(data.toResponseCatchRelease())
        }
    }


}