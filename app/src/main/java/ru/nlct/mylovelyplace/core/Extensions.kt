package ru.nlct.mylovelyplace.core

import com.google.firebase.firestore.DocumentSnapshot
import ru.nlct.mylovelyplace.data.home.database.entity.PlaceEntity
import ru.nlct.mylovelyplace.domain.models.Place

fun PlaceEntity.toPlace() = Place (
    id = id,
    title = title,
    content = content,
    imageLink = imageLink,
    latitude = latitude,
    longitude = longitude
)

fun DocumentSnapshot.toPlace(): Place = Place(
    data?.get(CommonConsts.PLACE_ID) as Long,
    data?.get(CommonConsts.PLACE_TITLE) as String,
    data?.get(CommonConsts.PLACE_CONTENT) as String,
    data?.get(CommonConsts.PLACE_IMAGELINK) as String,
    data?.get(CommonConsts.PLACE_LATITUDE) as Double,
    data?.get(CommonConsts.PLACE_LONGITUDE) as Double,
)