package ru.nct.mylovelyplace.di.module

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import ru.nct.mylovelyplace.data.PlaceRepositoryImpl
import ru.nct.mylovelyplace.data.database.PlaceDAO
import ru.nct.mylovelyplace.data.database.PlaceDatabase
import ru.nct.mylovelyplace.data.firebase.FirebaseDataSource
import ru.nct.mylovelyplace.data.firebase.FirebaseDataSourceImpl
import ru.nct.mylovelyplace.di.ApplicationScope
import ru.nct.mylovelyplace.domain.PlaceRepository
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    fun bindsPlaceRepository(placeRepository: PlaceRepositoryImpl): PlaceRepository = placeRepository

    @Provides
    fun providePlaceDao(db: PlaceDatabase): PlaceDAO = db.place()

    @Provides
    @ApplicationScope
    fun provideDatabase(context: Context): PlaceDatabase =
        Room.databaseBuilder(
            context,
            PlaceDatabase::class.java,
            PlaceDatabase.NAME
        ).createFromAsset("${PlaceDatabase.NAME}.db")
            .build()

    @Provides
    @ApplicationScope
    fun provideFirebaseDataSource(firebaseFirestore: FirebaseFirestore): FirebaseDataSource {
        return FirebaseDataSourceImpl(firebaseFirestore)
    }

    @Provides
    @ApplicationScope
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}