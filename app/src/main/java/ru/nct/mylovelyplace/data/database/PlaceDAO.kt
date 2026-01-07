package ru.nct.mylovelyplace.data.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlaceDAO {
    @Query("Select * From ${PlaceEntity.Schema.TABLE_NAME}")
    suspend fun getAllPlaces(): List<PlaceEntity>

    @Query("Select * From ${PlaceEntity.Schema.TABLE_NAME} WHERE ${PlaceEntity.Schema.ID} = :id")
    suspend fun getPlaceById(id: Long): PlaceEntity
}