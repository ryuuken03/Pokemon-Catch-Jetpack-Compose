package com.pokeapi.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pokeapi.data.source.local.entity.MyPokemonEntity

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Dao
interface AppDao {
    @Query("SELECT * FROM MyPokemonEntity ORDER By id DESC")
    fun getListMyPokemon(): MutableList<MyPokemonEntity>

    @Query("SELECT * FROM MyPokemonEntity WHERE id =:id")
    fun getMyPokemon(id : Int): MyPokemonEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMyPokemon(myPokemon: MyPokemonEntity)

    @Query("DELETE FROM MyPokemonEntity WHERE id = :id")
    fun deleteMyPokemon(id : Int)
}