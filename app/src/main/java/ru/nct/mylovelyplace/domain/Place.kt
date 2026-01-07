package ru.nct.mylovelyplace.domain

data class Place (
    val id: Long = 0,
    val title: String = "",
    val content: String? = "",
    val imageLink: String? = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)