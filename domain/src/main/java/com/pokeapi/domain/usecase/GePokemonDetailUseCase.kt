package com.pokeapi.domain.usecase

import com.pokeapi.domain.model.ResponsePokemonDetail
import com.pokeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
class GePokemonDetailUseCase @Inject constructor(
    private val repository: Repository
) :BaseUseCase<Int , Flow<ResponsePokemonDetail>>(){
    override suspend fun execute(params: Int): Flow<ResponsePokemonDetail> {
        return repository.getDetailPokemon(params)
    }
}