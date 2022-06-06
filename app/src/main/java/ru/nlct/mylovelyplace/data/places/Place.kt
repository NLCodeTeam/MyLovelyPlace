package ru.nlct.mylovelyplace.data.places

data class Place(
    val id: String,
    val imageLink: String,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    val title: String,
    val content: String,
)