package com.beatriz.eventos.data.model

data class EventResponse(
    val date: Long,
    val description: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val price: Float,
    val title: String,
    val id: Int
)
