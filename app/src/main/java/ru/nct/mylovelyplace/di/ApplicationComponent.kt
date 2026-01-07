package ru.nct.mylovelyplace.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nct.mylovelyplace.di.module.DataModule
import ru.nct.mylovelyplace.di.module.ViewModelModule

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}