package ru.nct.mylovelyplace.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nct.mylovelyplace.data.database.PlaceDatabase.Companion.VERSION

@Database(
    entities = [PlaceEntity::class],
    version = VERSION,
    exportSchema = false
)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun place(): PlaceDAO

    companion object {
        const val VERSION = 1
        const val NAME = "MyLovelyPlace"
    }
}