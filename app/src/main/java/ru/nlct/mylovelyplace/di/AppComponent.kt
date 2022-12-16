package ru.nlct.mylovelyplace.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nlct.mylovelyplace.BaseFragment
import ru.nlct.mylovelyplace.MainActivity
import javax.inject.Singleton

/**
 * App component - компонент даггера
 *
 * @author Marianna Sabanchieva
 */

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(frag: BaseFragment)
    fun inject(activity: MainActivity)
    fun context() : Context

    @Component.Builder
    interface BuilderComponent {
        @BindsInstance
        fun appModule(application: Application): BuilderComponent
        fun buildAppComp(): AppComponent
    }
}