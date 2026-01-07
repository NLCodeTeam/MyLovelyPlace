package ru.nct.mylovelyplace.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nct.mylovelyplace.PlaceViewModel
import ru.nct.mylovelyplace.di.ViewModelFactory
import ru.nct.mylovelyplace.di.ViewModelKey

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PlaceViewModel::class)
    abstract fun bindsPlaceViewModel(viewModel: PlaceViewModel): ViewModel
}