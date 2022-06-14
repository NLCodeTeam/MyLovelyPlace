package ru.nlct.mylovelyplace.domain.places

import android.net.Uri

interface PlaceRepository {
    suspend fun getPlaceByIdRemote(placeId: String): Place
    suspend fun addPlaceRemote(newPlace: Place, placeImageUri: Uri): String
    suspend fun deletePlaceRemote(placeId: String)
    suspend fun updatePlaceRemote(place: Place)
    suspend fun changePlaceImageRemote(placeId: String, newImageUri: Uri)
    suspend fun getPlacesRemote(): List<Place>
}