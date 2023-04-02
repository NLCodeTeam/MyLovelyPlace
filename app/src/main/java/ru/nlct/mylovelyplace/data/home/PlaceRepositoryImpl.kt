package ru.nlct.mylovelyplace.data.home

import ru.nlct.mylovelyplace.database.dao.PlaceDAO
import ru.nlct.mylovelyplace.domain.models.Place
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository
import javax.inject.Inject
import ru.nlct.mylovelyplace.extensions.ToPlace

/**
 * Реализация методов репозитория
 *
 * @author Marianna Sabanchieva
 */

class PlaceRepositoryImpl @Inject constructor(private val place: PlaceDAO) : PlaceRepository {
    override fun getPlaces(): List<Place> = place.getAllPlaces().map { it.ToPlace() }
    override fun getPlaceById(id: Long): Place = place.getPlaceById(id).ToPlace()
}