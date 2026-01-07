package ru.nct.mylovelyplace.domain

interface PlaceRepository {
    suspend fun getPlaces(): List<Place>

    suspend fun getPlaceById(id: Long): Place

    suspend fun getPlacesFromRemote(): List<Place>

    suspend fun getPlacesFromRemoteById(id: Long): Place
}