package ru.nlct.mylovelyplace.domain.comments


/**
 * Comment
 * Дата-класс, описывающий комментарий к месту
 * @property placeId - Id места, к которому комментарий
 * @property text - текст комментария
 * @property author - автор комментария
 * @author Камбиев Б. М. 15.06.2022
 */
data class Comment(
    val placeId: String,
    val text: String,
    val author: String
)
