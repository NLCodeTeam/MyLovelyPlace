package ru.nlct.mylovelyplace.domain.home.usecases

import ru.nlct.mylovelyplace.database.entity.PlaceEntity
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository
import javax.inject.Inject

/**
 * Get place use case
 *
 * @author Marianna Sabanchieva
 */

interface GetPlaceUseCase {
    fun getPlaces(): List<PlaceEntity>
    fun getPlaceById(id: Long): PlaceEntity
}

class GetPlaceUseCaseImpl @Inject constructor(private val placeRepository: PlaceRepository): GetPlaceUseCase {
    override fun getPlaces(): List<PlaceEntity> = placeRepository.getPlaces()
    override fun getPlaceById(id: Long): PlaceEntity = placeRepository.getPlaceById(id)
}