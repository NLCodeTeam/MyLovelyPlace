package ru.nlct.mylovelyplace.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.nlct.mylovelyplace.DatabaseConst.DATABASE_NAME
import ru.nlct.mylovelyplace.DatabaseConst.DATABASE_VERSION
import ru.nlct.mylovelyplace.database.dao.Place
import ru.nlct.mylovelyplace.database.entity.PlaceEntity

/**
 * Place database - объявление базы данных
 *
 * @author Marianna Sabanchieva
 */

@Database(entities = [PlaceEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun place(): Place

    companion object {
        @Volatile
        private var INSTANCE: PlaceDatabase? = null
        fun getInstance(context: Context): PlaceDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        PlaceDatabase::class.java,
                        DATABASE_NAME
                    )
                        .createFromAsset("$DATABASE_NAME.db")
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}