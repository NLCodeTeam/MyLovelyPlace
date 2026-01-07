package ru.nct.mylovelyplace

import androidx.lifecycle.ViewModel
import ru.nct.mylovelyplace.domain.usecase.GetPlacesByIdUseCase
import ru.nct.mylovelyplace.domain.usecase.GetPlacesUseCase
import javax.inject.Inject

class PlaceViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getPlacesByIdUseCase: GetPlacesByIdUseCase
): ViewModel() {

}