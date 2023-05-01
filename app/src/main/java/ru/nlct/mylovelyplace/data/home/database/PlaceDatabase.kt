package ru.nlct.mylovelyplace.data.home.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nlct.mylovelyplace.core.DatabaseConst.DATABASE_VERSION
import ru.nlct.mylovelyplace.data.home.database.dao.PlaceDAO
import ru.nlct.mylovelyplace.data.home.database.entity.PlaceEntity

/**
 * Place database - объявление базы данных
 *
 * @author Marianna Sabanchieva
 */

@Database(entities = [PlaceEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun place(): PlaceDAO
}