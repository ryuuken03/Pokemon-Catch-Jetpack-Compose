package com.pokeapi.data.repository

import android.content.Context
import com.pokeapi.data.source.local.AppDatabase
import com.pokeapi.data.source.local.entity.MyPokemonEntity
import com.pokeapi.data.source.local.entity.toDBMyPokemon
import com.pokeapi.domain.model.DBMyPokemon
import com.pokeapi.domain.repository.DatabaseRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 09/01/2024
 */
@Singleton
class DatabaseRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : DatabaseRepository{

    override suspend fun getListMyPokemon(): Flow<MutableList<DBMyPokemon>> {
        return flowOf(db.appDao().getListMyPokemon().map { it.toDBMyPokemon() }.toMutableList())
    }

    override suspend fun getMyPokemon(id: Int): Flow<DBMyPokemon> {
        return flowOf(db.appDao().getMyPokemon(id).toDBMyPokemon())
    }

    override suspend fun insertMyPokemon(
        id: Int,
        name: String,
        image: String,
        nickname: String,
        index: Int,
        fibbonaci: Int){
        var data = MyPokemonEntity()
        data.id = id
        data.name = name
        data.image = image
        data.nickname = nickname
        data.index = index
        data.fibbonaci = fibbonaci
        db.appDao().insertMyPokemon(data)
    }

    override suspend fun deleteMyPokemon(id:Int){
        db.appDao().deleteMyPokemon(id)
    }
}