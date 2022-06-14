package ru.nlct.mylovelyplace.domain.places


/**
 * Place
 * Дата-класс, описывающий место
 * @property id - Id места
 * @property imageLink - ссиылка на картинку в Firebase
 * @property latitude - координата-широта
 * @property longitude - координата-долгота
 * @property title - название
 * @property content - подбробное описание
 * @author Камбиев Б. М. 15.06.2022
 */
data class Place(
    val id: String,
    val imageLink: String,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    val title: String,
    val content: String,
)
