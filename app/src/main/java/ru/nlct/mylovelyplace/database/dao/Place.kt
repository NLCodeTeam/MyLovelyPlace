package ru.nlct.mylovelyplace.database.dao

import androidx.room.Dao
import androidx.room.Query
import ru.nlct.mylovelyplace.DatabaseConst.ID
import ru.nlct.mylovelyplace.DatabaseConst.PLACE
import ru.nlct.mylovelyplace.database.entity.PlaceEntity

/**
 * Place - DAO для базы данных, описывающая запросы к базе
 *
 * @author Marianna Sabanchieva
 */

@Dao
interface Place {
    @Query("Select * From $PLACE")
    fun getAllPlaces(): List<PlaceEntity>

    @Query("Select * From $PLACE WHERE $ID = :id")
    fun getPlaceById(id: Long): PlaceEntity
}