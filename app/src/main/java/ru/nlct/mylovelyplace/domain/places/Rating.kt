package ru.nlct.mylovelyplace.domain.places

/**
 * Rating
 * Дата-класс, несущий информацию о рейтинге места
 * @property placeId - Id места рейтинга
 * @property count - число проголосовавших
 * @property rating - рейтинг
 * @author Камбиев Б. М. 15.06.2022
 */
data class Rating(
    val placeId: String,
    val count: Int,
    val rating: Float
)
