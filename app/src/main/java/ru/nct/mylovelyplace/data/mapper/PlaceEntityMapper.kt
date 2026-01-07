package ru.nct.mylovelyplace.data.mapper

import ru.nct.mylovelyplace.data.database.PlaceEntity
import ru.nct.mylovelyplace.domain.Mapper
import ru.nct.mylovelyplace.domain.Place
import javax.inject.Inject

class PlaceEntityMapper @Inject constructor(): Mapper<PlaceEntity, Place> {
    override fun invoke(input: PlaceEntity): Place = with(input) {
        Place (
            id = id,
            title = title,
            content = content,
            imageLink = imageLink,
            latitude = latitude,
            longitude = longitude
        )
    }
}