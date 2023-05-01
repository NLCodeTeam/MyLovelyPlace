package ru.nlct.mylovelyplace.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import ru.nlct.mylovelyplace.core.DatabaseConst.DATABASE_NAME
import ru.nlct.mylovelyplace.data.home.database.PlaceDatabase
import ru.nlct.mylovelyplace.data.home.database.dao.PlaceDAO
import javax.inject.Singleton
import ru.nlct.mylovelyplace.data.places.FirebaseDataSource
import ru.nlct.mylovelyplace.data.places.FirebaseDataSourceImpl

/**
 * App module - модуль для объявления контекста и создания базы данных из файла,
 * а также разрешения контекста удаленного источника (Firebase)
 * @author Marianna Sabanchieva
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app

    @Provides
    fun provideChannelDao(appDatabase: PlaceDatabase): PlaceDAO = appDatabase.place()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PlaceDatabase =
        Room.databaseBuilder(context, PlaceDatabase::class.java, DATABASE_NAME)
            .createFromAsset("$DATABASE_NAME.db")
            .build()

    @Provides
    @Singleton
    fun provideFirebaseDataSource(firebaseFirestore: FirebaseFirestore): FirebaseDataSource {
        return FirebaseDataSourceImpl(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}