package ru.nlct.mylovelyplace

import org.osmdroid.util.GeoPoint

object DatabaseConst{
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyLovelyPlace"

    const val PLACE = "places"
    const val ID = "id"
    const val TITLE = "title"
    const val CONTENT = "content"
    const val IMG = "imageLink"
    const val LAT = "lat"
    const val LON = "lon"

    val FirstPoint = GeoPoint(43.3469353, 42.4528694)
    const val Zoom = 9.5
    const val ZoomTo = 15.0
}