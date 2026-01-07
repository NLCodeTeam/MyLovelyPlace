package ru.nct.mylovelyplace.data

import ru.nct.mylovelyplace.data.database.PlaceDAO
import ru.nct.mylovelyplace.data.firebase.FirebaseDataSource
import ru.nct.mylovelyplace.data.mapper.DocumentSnapshotMapper
import ru.nct.mylovelyplace.data.mapper.PlaceEntityMapper
import ru.nct.mylovelyplace.domain.Place
import ru.nct.mylovelyplace.domain.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val place: PlaceDAO,
    private val remotePlaces: FirebaseDataSource,
    private val placeEntityMapper: PlaceEntityMapper,
    private val documentSnapshotMapper: DocumentSnapshotMapper
) : PlaceRepository {

    override suspend fun getPlaces(): List<Place> = place.getAllPlaces()
        .map { placeEntityMapper(it) }

    override suspend fun getPlaceById(id: Long): Place = placeEntityMapper(place.getPlaceById(id))

    override suspend fun getPlacesFromRemote(): List<Place> = remotePlaces
        .getCollection(REMOTE_PLACES_COLLECTION_NAME)
        .map { documentSnapshotMapper(it) }

    override suspend fun getPlacesFromRemoteById(id: Long): Place =
        documentSnapshotMapper(remotePlaces
            .getDocument(REMOTE_PLACES_COLLECTION_NAME, id.toString())
        )

    companion object {
        private const val REMOTE_PLACES_COLLECTION_NAME = "Places"
    }
}
