package ru.nlct.mylovelyplace.domain.places

import android.net.Uri


/**
 * PlaceRepository
 * Интерфейс, описывающий методы репозитория для работы с [Place]
 * @author Камбиев Б. М. 15.06.2022
 */
interface PlaceRepository {
    /** Метод возвращает Место [Place] из Firebase по его Id */
    suspend fun getPlaceByIdRemote(placeId: String): Place

    /** Метод добавляет новое Место [Place] и его картинку, переданную как Uri в Firebase */
    suspend fun addPlaceRemote(newPlace: Place, placeImageUri: Uri): String

    /** Метод удаляет Место из Firebase по его Id */
    suspend fun deletePlaceRemote(placeId: String)

    /** Метод обновляет данные Места [Place] в Firebase */
    suspend fun updatePlaceRemote(place: Place)

    /** Метод меняет картинку Места в Firebase с сохранением Id картинки */
    suspend fun changePlaceImageRemote(placeId: String, newImageUri: Uri)

    /** Метод возвращает коллекцию мест [Place] из Firebase, начиная с документа, который после документа со  startDocumentId, количество count */
    suspend fun getPlacesRemote(startPlaceId: String, count: Long): List<Place>
}