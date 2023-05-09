package ru.nlct.mylovelyplace.domain.models

/**
 * Place - модель места
 *
 * @property id - идентификатор места
 * @property title - наименование
 * @property content - описание
 * @property imageLink - ссылка на изображение
 * @property latitude - широта
 * @property longitude - долгота
 * @author Beslan Kambiev
 */
data class Place (
    val id: Long = 0,
    val title: String = "",
    val content: String? = "",
    val imageLink: String? = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)
