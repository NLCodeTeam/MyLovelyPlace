package ru.nlct.mylovelyplace.data.repositories

import javax.inject.Inject
import ru.nlct.mylovelyplace.core.CommonConsts
import ru.nlct.mylovelyplace.data.home.database.dao.PlaceDAO
import ru.nlct.mylovelyplace.data.places.FirebaseDataSource
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository
import ru.nlct.mylovelyplace.domain.models.Place
import ru.nlct.mylovelyplace.core.toPlace

/**
 * Реализация методов репозитория
 *
 * @author Marianna Sabanchieva
 */

class PlaceRepositoryImpl @Inject constructor(private val place: PlaceDAO, private val remotePlaces: FirebaseDataSource) : PlaceRepository {

    override fun getPlaces(): List<Place> = place.getAllPlaces().map { it.toPlace() }

    override fun getPlaceById(id: Long): Place = place.getPlaceById(id).toPlace()

    override suspend fun getPlacesFromRemote(): List<Place> = remotePlaces
        .getCollection(CommonConsts.REMOTE_PLACES_COLLECTION_NAME)
        .map { it.toPlace() }

    override suspend fun getPlacesFromRemoteById(Id: Long): Place = remotePlaces
        .getDocument(CommonConsts.REMOTE_PLACES_COLLECTION_NAME, Id.toString()).toPlace()
}
