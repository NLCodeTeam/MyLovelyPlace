package ru.nlct.mylovelyplace.data.home

import ru.nlct.mylovelyplace.database.dao.PlaceDAO
import ru.nlct.mylovelyplace.domain.home.entity.Place
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository
import javax.inject.Inject

/**
 * Реализация методов репозитория
 *
 * @author Marianna Sabanchieva
 */

class PlaceRepositoryImpl @Inject constructor(private val place: PlaceDAO) : PlaceRepository {
    override fun getPlaces(): List<Place> = place.getAllPlaces()
    override fun getPlaceById(id: Long): Place = place.getPlaceById(id)
}