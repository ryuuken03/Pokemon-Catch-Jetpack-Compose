package com.pokeapi.data.di

import com.pokeapi.data.repository.DatabaseRepositoryImpl
import com.pokeapi.data.repository.RepositoryImpl
import com.pokeapi.data.source.local.AppDatabase
import com.pokeapi.data.source.remote.ApiService
import com.pokeapi.domain.repository.DatabaseRepository
import com.pokeapi.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRepositoryImpl(
        apiService: ApiService,
    ): Repository = RepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideDatabaseRepositoryImpl(
        appDatabase: AppDatabase,
    ): DatabaseRepository = DatabaseRepositoryImpl(appDatabase)
}
