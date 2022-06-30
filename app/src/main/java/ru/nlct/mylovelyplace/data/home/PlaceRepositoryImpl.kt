package ru.nlct.mylovelyplace.data.home

import ru.nlct.mylovelyplace.database.dao.Place
import ru.nlct.mylovelyplace.database.entity.PlaceEntity
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository
import javax.inject.Inject

/**
 * Реализация методов репозитория
 *
 * @author Marianna Sabanchieva
 */

class PlaceRepositoryImpl @Inject constructor(private val place: Place) : PlaceRepository {
    override fun getPlaces(): List<PlaceEntity> = place.getAllPlaces()
    override fun getPlaceById(id: Long): PlaceEntity = place.getPlaceById(id)
}