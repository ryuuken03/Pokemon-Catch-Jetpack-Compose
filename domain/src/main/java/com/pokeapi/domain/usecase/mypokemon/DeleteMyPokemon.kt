package com.pokeapi.domain.usecase.mypokemon

import com.pokeapi.domain.repository.DatabaseRepository
import com.pokeapi.domain.usecase.BaseUseCase
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
class DeleteMyPokemon @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : BaseUseCase<String, Unit>() {
    override suspend fun execute(params: String) {
        return databaseRepository.deleteMyPokemon(params.toInt())
    }
}