package com.pokeapi.domain.usecase.mypokemon

import android.util.Log
import com.pokeapi.domain.model.DBMyPokemon
import com.pokeapi.domain.repository.DatabaseRepository
import com.pokeapi.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
class GetMyPokemon @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : BaseUseCase<String, Flow<DBMyPokemon>>() {
    override suspend fun execute(params: String) : Flow<DBMyPokemon> {
        return databaseRepository.getMyPokemon(params.toInt())
    }
}