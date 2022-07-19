package ru.nlct.mylovelyplace.domain.home.repository

import ru.nlct.mylovelyplace.domain.home.entity.Place

/**
 * Репозиторий для списка предустановленных мест их детализации
 *
 * @author Marianna Sabanchieva
 */

interface PlaceRepository {
    fun getPlaces(): List<Place>
    fun getPlaceById(id: Long): Place
}