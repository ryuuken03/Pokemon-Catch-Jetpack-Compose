package com.pokeapi.domain.usecase.mypokemon

import com.pokeapi.domain.model.DBMyPokemon
import com.pokeapi.domain.repository.DatabaseRepository
import com.pokeapi.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 09/01/2024
 */
class GetListMyPokemon @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : BaseUseCase<Unit, Flow<MutableList<DBMyPokemon>>>() {
    override suspend fun execute(params: Unit) :Flow<MutableList<DBMyPokemon>> {
        return databaseRepository.getListMyPokemon()
    }
}