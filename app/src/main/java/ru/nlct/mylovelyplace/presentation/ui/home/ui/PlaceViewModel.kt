package ru.nlct.mylovelyplace.presentation.ui.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nlct.mylovelyplace.database.entity.PlaceEntity
import ru.nlct.mylovelyplace.domain.home.usecases.GetPlaceUseCaseImpl
import javax.inject.Inject

/**
 * Place view model - для хранения списка предустановленных мест
 *
 * @author Marianna Sabanchieva
 */

class PlaceViewModel @Inject constructor(
    private val useCase: GetPlaceUseCaseImpl
) : ViewModel() {

    private val _placeLoaded = MutableLiveData<List<PlaceEntity>>()
    val allPlaces: LiveData<List<PlaceEntity>> get() = _placeLoaded

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _placeLoaded.postValue(useCase.getPlaces())
        }
    }
}