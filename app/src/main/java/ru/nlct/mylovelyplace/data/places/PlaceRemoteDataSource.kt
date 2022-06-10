package ru.nlct.mylovelyplace.data.places

import ru.nlct.mylovelyplace.domain.places.Place

interface PlaceRemoteDataSource {

    suspend fun addPlace(newPlace: PlaceData): String
    suspend fun updatePlace(placeId: String, newPlaceData: PlaceData)
    suspend fun deletePlace(placeId: String)
    suspend fun getPlaces(): List<Place>
    suspend fun getPlaceById(placeId: String): Place

}