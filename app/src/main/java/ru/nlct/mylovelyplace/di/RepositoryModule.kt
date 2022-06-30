package ru.nlct.mylovelyplace.di

import dagger.Binds
import dagger.Module
import ru.nlct.mylovelyplace.data.home.PlaceRepositoryImpl
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindsPlaceRepository(placeRepository: PlaceRepositoryImpl): PlaceRepository
}