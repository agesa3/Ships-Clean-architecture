package com.agesadev.domain.di

import com.agesadev.domain.repository.ShipsRepository
import com.agesadev.domain.usecases.GetShipsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShipRepositoryModule {

//    @Provides
//    @Singleton
//    fun providesShipsUseCase(shipsRepository: ShipsRepository) = GetShipsUseCase(shipsRepository)

}