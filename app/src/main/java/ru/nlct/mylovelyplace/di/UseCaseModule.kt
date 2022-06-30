package ru.nlct.mylovelyplace.di

import dagger.Binds
import dagger.Module
import ru.nlct.mylovelyplace.domain.home.usecases.GetPlaceUseCase
import ru.nlct.mylovelyplace.domain.home.usecases.GetPlaceUseCaseImpl

@Module
interface UseCaseModule {
    @Binds
    fun bindsPostsUseCase(useCase: GetPlaceUseCaseImpl): GetPlaceUseCase
}