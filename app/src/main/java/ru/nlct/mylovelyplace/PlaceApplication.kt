package ru.nlct.mylovelyplace

import android.app.Application
import ru.nlct.mylovelyplace.di.AppComponent
import ru.nlct.mylovelyplace.di.DaggerAppComponent

/**
 * Place application - инициализирует компонент даггера
 *
 * @author Marianna Sabanchieva
 */

open class PlaceApplication: Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()

        initComponent()
    }

    protected open fun initComponent() {
        dagger = DaggerAppComponent
            .builder()
            .appModule(this)
            .buildAppComp()
    }
}