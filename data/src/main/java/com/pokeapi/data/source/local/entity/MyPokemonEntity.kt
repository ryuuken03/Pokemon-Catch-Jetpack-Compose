package com.pokeapi.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pokeapi.data.source.remote.dto.PokemonDto
import com.pokeapi.domain.model.DBMyPokemon
import com.pokeapi.domain.model.Pokemon

/***
 * Created By Mohammad Toriq on 09/01/2024
 */
@Entity
data class MyPokemonEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: Int?= null,

    @ColumnInfo(name = "name")
    var name: String?= null,

    @ColumnInfo(name = "image")
    var image: String?= null,

    @ColumnInfo(name = "nickname")
    var nickname: String?= null,

    @ColumnInfo(name = "index")
    var index: Int?= null,

    @ColumnInfo(name = "fibbonaci")
    var fibbonaci: Int?= null,
)

fun MyPokemonEntity.toDBMyPokemon(): DBMyPokemon {
    return DBMyPokemon(
        id = id,
        name = name,
        image = image,
        nickname = nickname,
        index = index,
        fibbonaci = fibbonaci,
    )
}
