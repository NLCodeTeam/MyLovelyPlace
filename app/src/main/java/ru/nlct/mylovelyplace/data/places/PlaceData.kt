package ru.nlct.mylovelyplace.data.places

data class PlaceData(
    val imageLink: String,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    val title: String,
    val content: String,
)
