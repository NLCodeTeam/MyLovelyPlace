package ru.nct.mylovelyplace.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import ru.nct.mylovelyplace.domain.Mapper
import ru.nct.mylovelyplace.domain.Place
import javax.inject.Inject

class DocumentSnapshotMapper @Inject constructor(): Mapper<DocumentSnapshot, Place> {
    override fun invoke(input: DocumentSnapshot): Place = with(input) {
        Place(
            data?.get(PLACE_ID) as Long,
            data?.get(PLACE_TITLE) as String,
            data?.get(PLACE_CONTENT) as String,
            data?.get(PLACE_IMAGE_LINK) as String,
            data?.get(PLACE_LATITUDE) as Double,
            data?.get(PLACE_LONGITUDE) as Double,
        )
    }
    
    private companion object {
        const val PLACE_ID = "id"
        const val PLACE_TITLE = "title"
        const val PLACE_CONTENT = "content"
        const val PLACE_IMAGE_LINK = "imageLink"
        const val PLACE_LATITUDE = "latitude"
        const val PLACE_LONGITUDE = "longitude"
    }
}