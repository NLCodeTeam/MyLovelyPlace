package ru.nlct.mylovelyplace.domain.notifications

import com.google.type.DateTime


/**
 * Notification
 * Дата-класс, описывающий зарегистрированное событие
 * @property placeId
 * @property title
 * @property content
 * @property creator
 * @property timeSchedule
 * @property countOfLikes
 * @author Камбиев Б. М. 15.06.2022
 */
data class Notification(
    val id: String,
    val placeId: String,
    val title: String,
    val content: String,
    val creator: String,
    val timeSchedule: DateTime,
    val countOfLikes: Int
)
