package ru.nlct.mylovelyplace.domain.home.repository

import ru.nlct.mylovelyplace.database.entity.PlaceEntity

/**
 * Репозиторий для списка предустановленных мест их детализации
 *
 * @author Marianna Sabanchieva
 */

interface PlaceRepository {
    fun getPlaces(): List<PlaceEntity>
    fun getPlaceById(id: Long): PlaceEntity
}