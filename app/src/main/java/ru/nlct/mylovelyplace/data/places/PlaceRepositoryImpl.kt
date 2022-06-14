package ru.nlct.mylovelyplace.data.places

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot
import ru.nlct.mylovelyplace.data.Consts
import ru.nlct.mylovelyplace.data.datasource.RemoteDataSource
import ru.nlct.mylovelyplace.domain.places.Place
import ru.nlct.mylovelyplace.domain.places.PlaceRepository

class PlaceRepositoryImpl(

    private val remoteDataSource: RemoteDataSource

) : PlaceRepository {

    override suspend fun getPlaceByIdRemote(placeId: String): Place {
        val place = remoteDataSource.getDocumentById(Consts.COLL_PLACES, placeId)
        return documentSnapshotToPlace(place)
    }

    override suspend fun addPlaceRemote(newPlace: Place, placeImageUri: Uri): String {
        val newPlaceMap = placeToMap(newPlace)
        val placeId = remoteDataSource.addDocument(Consts.COLL_PLACES, newPlaceMap)
        remoteDataSource.addFile(placeId, placeImageUri)
        return placeId
    }

    override suspend fun deletePlaceRemote(placeId: String) {
        remoteDataSource.deleteDocument(Consts.COLL_PLACES, placeId)
        remoteDataSource.deleteFile(placeId)
    }

    override suspend fun updatePlaceRemote(place: Place) {
        val newPlaceMap = placeToMap(place)
        remoteDataSource.updateDocument(Consts.COLL_PLACES, place.id, newPlaceMap)
    }

    override suspend fun changePlaceImageRemote(placeId: String, newImageUri: Uri) {
        remoteDataSource.deleteFile(placeId)
        remoteDataSource.addFile(placeId, newImageUri)
    }

    override suspend fun getPlacesRemote(): List<Place> {
        return remoteDataSource.getCollection(Consts.COLL_PLACES)
            .map { documentSnapshot ->  documentSnapshotToPlace(documentSnapshot)}
    }

    private fun placeToMap(place: Place): Map<String, Any> {
        return mapOf(
            Consts.FIELD_IMAGELINK to place.imageLink,
            Consts.FIELD_LATITUDE to place.latitude,
            Consts.FIELD_LONGITUDE to place.longitude,
            Consts.FIELD_TITLE to place.title,
            Consts.FIELD_CONTENT to place.content
        )
    }

    private fun documentSnapshotToPlace(document: DocumentSnapshot): Place {
        return Place(
            document.id,
            document.data?.get(Consts.FIELD_IMAGELINK) as String,
            document.data?.get(Consts.FIELD_LATITUDE) as Double,
            document.data?.get(Consts.FIELD_LONGITUDE) as Double,
            document.data?.get(Consts.FIELD_TITLE) as String,
            document.data?.get(Consts.FIELD_CONTENT) as String
        )
    }
}