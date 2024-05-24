package com.pokeapi.domain.usecase.mypokemon

import com.pokeapi.domain.repository.DatabaseRepository
import com.pokeapi.domain.usecase.BaseUseCase
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 09/01/2024
 */
class InsertMyPokemon @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : BaseUseCase<List<String>, Unit>() {
    override suspend fun execute(params: List<String>) {
        var id = params[0].toInt()
        var name = params[1]
        var image = params[2]
        var nickname = params[3]
        var index = params[4].toInt()
        var fibbonaci = params[5].toInt()
        return databaseRepository.insertMyPokemon(id,name,image,nickname,index,fibbonaci)
    }
}