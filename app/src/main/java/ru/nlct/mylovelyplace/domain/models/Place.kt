package ru.nlct.mylovelyplace.domain.models

data class Place (
    val id: Long = 0,
    val title: String = "",
    val content: String? = "",
    val imageLink: String? = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)
