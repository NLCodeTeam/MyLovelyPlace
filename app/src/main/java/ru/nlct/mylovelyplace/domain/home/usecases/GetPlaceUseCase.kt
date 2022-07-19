package ru.nlct.mylovelyplace.domain.home.usecases

import ru.nlct.mylovelyplace.domain.home.entity.Place
import ru.nlct.mylovelyplace.domain.home.repository.PlaceRepository
import javax.inject.Inject

/**
 * Get place use case
 *
 * @author Marianna Sabanchieva
 */

interface GetPlaceUseCase {
    fun getPlaces(): List<Place>
    fun getPlaceById(id: Long): Place
}

class GetPlaceUseCaseImpl @Inject constructor(private val placeRepository: PlaceRepository): GetPlaceUseCase {
    override fun getPlaces(): List<Place> = placeRepository.getPlaces()
    override fun getPlaceById(id: Long): Place = placeRepository.getPlaceById(id)
}