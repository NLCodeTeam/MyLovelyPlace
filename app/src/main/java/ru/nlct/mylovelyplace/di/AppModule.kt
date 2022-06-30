package ru.nlct.mylovelyplace.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nlct.mylovelyplace.DatabaseConst.DATABASE_NAME
import ru.nlct.mylovelyplace.database.PlaceDatabase
import ru.nlct.mylovelyplace.database.dao.Place
import javax.inject.Singleton

/**
 * App module - модуль для объявления контекста и создания базы данных из файла
 *
 * @author Marianna Sabanchieva
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app

    @Provides
    fun provideChannelDao(appDatabase: PlaceDatabase): Place = appDatabase.place()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PlaceDatabase =
        Room.databaseBuilder(context, PlaceDatabase::class.java, DATABASE_NAME)
            .createFromAsset("$DATABASE_NAME.db")
            .build()
}