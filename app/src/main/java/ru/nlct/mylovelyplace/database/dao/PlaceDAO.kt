package ru.nlct.mylovelyplace.database.dao

import androidx.room.Dao
import androidx.room.Query
import ru.nlct.mylovelyplace.domain.home.entity.Place

/**
 * Place - DAO для базы данных, описывающая запросы к базе
 *
 * @author Marianna Sabanchieva
 */

@Dao
interface PlaceDAO {
    @Query("Select * From ${Place.Schema.TABLE_NAME}")
    fun getAllPlaces(): List<Place>

    @Query("Select * From ${Place.Schema.TABLE_NAME} WHERE ${Place.Schema.ID} = :id")
    fun getPlaceById(id: Long): Place
}