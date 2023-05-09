package ru.nlct.mylovelyplace.data.home.database.dao

import androidx.room.Dao
import androidx.room.Query
import ru.nlct.mylovelyplace.data.home.database.entity.PlaceEntity

/**
 * Place - DAO для базы данных, описывающая запросы к базе
 *
 * @author Marianna Sabanchieva
 */

@Dao
interface PlaceDAO {
    @Query("Select * From ${PlaceEntity.Schema.TABLE_NAME}")
    fun getAllPlaces(): List<PlaceEntity>

    @Query("Select * From ${PlaceEntity.Schema.TABLE_NAME} WHERE ${PlaceEntity.Schema.ID} = :id")
    fun getPlaceById(id: Long): PlaceEntity
}