package ru.nct.mylovelyplace.domain.usecase

import ru.nct.mylovelyplace.domain.PlaceRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val repository: PlaceRepository) {
    suspend operator fun invoke() = repository.getPlaces()
}