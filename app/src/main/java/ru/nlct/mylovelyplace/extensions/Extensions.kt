package ru.nlct.mylovelyplace.extensions

import ru.nlct.mylovelyplace.database.entity.PlaceEntity
import ru.nlct.mylovelyplace.domain.models.Place

fun PlaceEntity.ToPlace() = Place (
    id = id,
    title = title,
    content = content,
    imageLink = imageLink,
    latitude = latitude,
    longitude = longitude
)