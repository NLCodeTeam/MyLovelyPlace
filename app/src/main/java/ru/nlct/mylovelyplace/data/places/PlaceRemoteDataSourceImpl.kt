package ru.nlct.mylovelyplace.data.places

import com.google.firebase.firestore.DocumentSnapshot
import ru.nlct.mylovelyplace.data.Consts
import ru.nlct.mylovelyplace.data.DocumentRemoteDataSource
import ru.nlct.mylovelyplace.domain.places.Place

internal class PlaceRemoteDataSourceImpl(

    private val documentRemoteDataSource: DocumentRemoteDataSource

) : PlaceRemoteDataSource {

    override suspend fun addPlace(newPlace: PlaceData): String {

        val newPlaceMap = placeDataToHashMap(newPlace)

        return documentRemoteDataSource.addDocument(Consts.COLL_PLACES, newPlaceMap)
    }

    override suspend fun updatePlace(placeId: String, newPlaceData: PlaceData) {

        val updatePlaceDataMap = placeDataToHashMap(newPlaceData)
        documentRemoteDataSource.updateDocument(Consts.COLL_PLACES, placeId, updatePlaceDataMap)

    }

    override suspend fun deletePlace(placeId: String) {
        documentRemoteDataSource.deleteDocument(Consts.COLL_PLACES, placeId)
    }

    override suspend fun getPlaces(): List<Place> {

        val resultCollection = documentRemoteDataSource.getCollection(Consts.COLL_PLACES)
        return resultCollection.map { documentSnapshot ->  documentPlaceToPlace(documentSnapshot) }

    }

    override suspend fun getPlaceById(placeId: String): Place {
        val documentPlace = documentRemoteDataSource.getDocumentById(Consts.COLL_PLACES, placeId)
        return documentPlaceToPlace(documentPlace)
    }

    private fun placeDataToHashMap(place: PlaceData): HashMap<String, Any> {
        val resultMap = HashMap<String, Any>()

        resultMap[Consts.FIELD_IMAGELINK] = place.imageLink
        resultMap[Consts.FIELD_LATITUDE] = place.latitude
        resultMap[Consts.FIELD_LONGITUDE] = place.longitude
        resultMap[Consts.FIELD_TITLE] = place.title
        resultMap[Consts.FIELD_CONTENT] = place.content

        return resultMap
    }

    private fun documentPlaceToPlace(document: DocumentSnapshot): Place {
        return Place(
            document.id,
            document.data!![Consts.FIELD_IMAGELINK] as String,
            document.data!![Consts.FIELD_LATITUDE] as Double,
            document.data!![Consts.FIELD_LONGITUDE] as Double,
            document.data!![Consts.FIELD_TITLE] as String,
            document.data!![Consts.FIELD_CONTENT] as String
        )
    }
}