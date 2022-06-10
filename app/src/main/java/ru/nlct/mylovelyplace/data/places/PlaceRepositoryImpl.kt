package ru.nlct.mylovelyplace.data.places

import android.net.Uri
import ru.nlct.mylovelyplace.data.RemoteStorage
import ru.nlct.mylovelyplace.domain.places.Place
import ru.nlct.mylovelyplace.domain.places.PlaceRepository

class PlaceRepositoryImpl(

    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val remoteStorage: RemoteStorage

) : PlaceRepository {

    override suspend fun getPlaceById(placeId: String): Place {
        return placeRemoteDataSource.getPlaceById(placeId)
    }

    override suspend fun addPlace(newPlace: Place, placeImageUri: Uri): String {
        val newPlaceData = placeToPlaceData(newPlace)
        val placeId = placeRemoteDataSource.addPlace(newPlaceData)
        remoteStorage.addFile(placeId, placeImageUri)
        return placeId
    }

    override suspend fun deletePlace(placeId: String) {
        placeRemoteDataSource.deletePlace(placeId)
        remoteStorage.deleteFile(placeId)
    }

    override suspend fun updatePlace(place: Place) {
        val newPlaceData = placeToPlaceData(place)
        placeRemoteDataSource.updatePlace(place.id, newPlaceData)
    }

    override suspend fun changePlaceImage(placeId: String, newImageUri: Uri) {
        remoteStorage.deleteFile(placeId)
        remoteStorage.addFile(placeId, newImageUri)
    }

    override suspend fun getPlaces(): List<Place> {
        return placeRemoteDataSource.getPlaces()
    }

    private fun placeToPlaceData(place: Place): PlaceData {
        return PlaceData(
            place.imageLink,
            place.latitude,
            place.longitude,
            place.title,
            place.content
        )
    }
}