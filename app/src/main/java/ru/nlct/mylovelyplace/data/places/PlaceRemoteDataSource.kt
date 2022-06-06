package ru.nlct.mylovelyplace.data.places

interface PlaceRemoteDataSource {

    suspend fun addPlace(newPlace: Place): String
    suspend fun updatePlace(place: Place): Boolean
    suspend fun deletePlace(placeId: String): Boolean
    suspend fun getPlaces(): List<Place>
    suspend fun getPlaceById(placeId: String): Place

}