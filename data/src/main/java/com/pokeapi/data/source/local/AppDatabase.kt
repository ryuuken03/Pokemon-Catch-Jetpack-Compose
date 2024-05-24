package com.pokeapi.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokeapi.data.source.local.dao.AppDao
import com.pokeapi.data.source.local.entity.MyPokemonEntity

/***
 * Created By Mohammad Toriq on 09/01/2024
 */
@Database(
    entities = [MyPokemonEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "m_pokemon_catch.db"
        const val DB_BACKUP_SUFFIX = "-bkp"
        const val SQLITE_WALFILE_SUFFIX = "-wal"
        const val SQLITE_SHMFILE_SUFFIX = "-shm"
    }
    abstract fun appDao(): AppDao
}