package com.beatriz.eventos.data.model

data class EventResponse(
    val date: Int,
    val description: String,
    val image: String,
    val longitude: Int,
    val latitude: Int,
    val price: Float,
    val title: String,
    val id: Int,
)
