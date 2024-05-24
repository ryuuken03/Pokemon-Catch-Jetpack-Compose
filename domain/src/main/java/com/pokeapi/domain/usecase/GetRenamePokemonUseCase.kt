package com.pokeapi.domain.usecase

import com.pokeapi.domain.model.ResponseCatchReleaseRename
import com.pokeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
class GetRenamePokemonUseCase @Inject constructor(
    private val repository: Repository
) :BaseUseCase<List<String> , Flow<ResponseCatchReleaseRename>>(){
    override suspend fun execute(params: List<String>): Flow<ResponseCatchReleaseRename> {
        return repository.getRenamePokemon(id = params[0].toInt(),name = params[1],index = params[2].toInt(), nickname = params[3])
    }
}