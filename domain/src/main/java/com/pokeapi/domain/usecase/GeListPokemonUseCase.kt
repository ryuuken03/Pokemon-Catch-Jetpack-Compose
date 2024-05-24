package com.pokeapi.domain.usecase

import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
class GeListPokemonUseCase @Inject constructor(
    private val repository: Repository
) :BaseUseCase<List<String> , Flow<ResponsePokemon>>(){
    override suspend fun execute(params: List<String>): Flow<ResponsePokemon> {
        return repository.getListPokemon(params[0].toInt(),params[1].toInt())
    }
}