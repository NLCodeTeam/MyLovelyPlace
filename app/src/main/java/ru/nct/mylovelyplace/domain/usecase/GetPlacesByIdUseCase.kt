package ru.nct.mylovelyplace.domain.usecase

import ru.nct.mylovelyplace.domain.PlaceRepository
import javax.inject.Inject

class GetPlacesByIdUseCase @Inject constructor(private val repository: PlaceRepository) {
    suspend operator fun invoke(id: Long) = repository.getPlaceById(id)
}