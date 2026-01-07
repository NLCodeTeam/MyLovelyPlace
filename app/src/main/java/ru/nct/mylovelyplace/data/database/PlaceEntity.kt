package ru.nct.mylovelyplace.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlaceEntity.Schema.TABLE_NAME)
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Schema.ID) val id: Long = 0,
    @ColumnInfo(name = Schema.TITLE) val title: String = "",
    @ColumnInfo(name = Schema.CONTENT) val content: String? = "",
    @ColumnInfo(name = Schema.IMG) val imageLink: String? = "",
    @ColumnInfo(name = Schema.LAT) val latitude: Double = 0.0,
    @ColumnInfo(name = Schema.LON) val longitude: Double = 0.0
) {
    object Schema {
        const val TABLE_NAME = "places"
        const val ID = "id"
        const val TITLE = "title"
        const val CONTENT = "content"
        const val IMG = "imageLink"
        const val LAT = "lat"
        const val LON = "lon"
    }
}