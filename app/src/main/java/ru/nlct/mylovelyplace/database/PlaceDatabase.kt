package ru.nlct.mylovelyplace.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nlct.mylovelyplace.DatabaseConst.DATABASE_VERSION
import ru.nlct.mylovelyplace.database.dao.PlaceDAO
import ru.nlct.mylovelyplace.domain.home.entity.Place

/**
 * Place database - объявление базы данных
 *
 * @author Marianna Sabanchieva
 */

@Database(entities = [Place::class], version = DATABASE_VERSION, exportSchema = false)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun place(): PlaceDAO
}