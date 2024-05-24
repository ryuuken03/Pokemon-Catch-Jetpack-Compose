package com.pokeapi.domain.usecase

import com.pokeapi.domain.model.ResponseCatchReleaseRename
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
class GetReleasePokemonUseCase @Inject constructor(
    private val repository: Repository
) :BaseUseCase<List<String> , Flow<ResponseCatchReleaseRename>>(){
    override suspend fun execute(params: List<String>): Flow<ResponseCatchReleaseRename> {
        return repository.getReleasePokemon(params[0].toInt(),params[1])
    }
}