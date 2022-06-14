package ru.nlct.mylovelyplace.data.places

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot
import ru.nlct.mylovelyplace.data.Consts
import ru.nlct.mylovelyplace.data.datasource.RemoteDataSource
import ru.nlct.mylovelyplace.domain.places.Place
import ru.nlct.mylovelyplace.domain.places.PlaceRepository


/**
 * PlaceRepositoryImpl
 * Класс, реализующий интерфейс [PlaceRepository]
 *
 * @author Камбиев Б. М. 15.06.2022
 */
class PlaceRepositoryImpl(

    private val remoteDataSource: RemoteDataSource

) : PlaceRepository {

    override suspend fun getPlaceByIdRemote(placeId: String): Place {
        val placeDocument = remoteDataSource.getDocumentById(Consts.PLACES, placeId)
        return documentSnapshotToPlace(placeDocument)
    }

    override suspend fun addPlaceRemote(newPlace: Place, placeImageUri: Uri): String {
        val newPlaceMap = placeToMap(newPlace)
        val placeId = remoteDataSource.addDocument(Consts.PLACES, newPlaceMap)
        val imageLink = remoteDataSource.addFile(placeId, placeImageUri)
        remoteDataSource.updateDocumentField(Consts.PLACES, placeId, Consts.PLACE_IMAGELINK, imageLink)
        return placeId
    }

    override suspend fun deletePlaceRemote(placeId: String) {
        remoteDataSource.deleteDocument(Consts.PLACES, placeId)
        remoteDataSource.deleteFile(placeId)
    }

    override suspend fun updatePlaceRemote(place: Place) {
        val newPlaceMap = placeToMap(place)
        remoteDataSource.updateDocument(Consts.PLACES, place.id, newPlaceMap)
    }

    override suspend fun changePlaceImageRemote(placeId: String, newImageUri: Uri) {
        remoteDataSource.deleteFile(placeId)
        remoteDataSource.addFile(placeId, newImageUri)
    }

    override suspend fun getPlacesRemote(startPlaceId: String, count: Long): List<Place> {
        return remoteDataSource.getCollection(Consts.PLACES, startPlaceId, count)
            .map { documentSnapshot ->  documentSnapshotToPlace(documentSnapshot)}
    }


    /** Вспомогательный метод, конвертирующий данные Места [Place]  в Map для записи в Firebase */
    private fun placeToMap(place: Place): Map<String, Any> {
        return mapOf(
            Consts.PLACE_IMAGELINK to place.imageLink,
            Consts.PLACE_LATITUDE to place.latitude,
            Consts.PLACE_LONGITUDE to place.longitude,
            Consts.PLACE_TITLE to place.title,
            Consts.PLACE_CONTENT to place.content
        )
    }

    /** Вспомогательный метод, конвертирующий данные полученные из Firebase из DocumentSnapshot
     *  в данные Места [Place] */
    private fun documentSnapshotToPlace(document: DocumentSnapshot): Place {
        return Place(
            document.id,
            document.data?.get(Consts.PLACE_IMAGELINK) as String,
            document.data?.get(Consts.PLACE_LATITUDE) as Double,
            document.data?.get(Consts.PLACE_LONGITUDE) as Double,
            document.data?.get(Consts.PLACE_TITLE) as String,
            document.data?.get(Consts.PLACE_CONTENT) as String
        )
    }
}