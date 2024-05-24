package com.pokeapi.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.pokeapi.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()
    }
}