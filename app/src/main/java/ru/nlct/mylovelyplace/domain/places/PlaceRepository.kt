package ru.nlct.mylovelyplace.domain.places

import android.net.Uri

interface PlaceRepository {
    suspend fun getPlaceById(placeId: String): Place
    suspend fun addPlace(newPlace: Place, placeImageUri: Uri): String
    suspend fun deletePlace(placeId: String)
    suspend fun updatePlace(place: Place)
    suspend fun changePlaceImage(placeId: String, newImageUri: Uri)
    suspend fun getPlaces(): List<Place>
}