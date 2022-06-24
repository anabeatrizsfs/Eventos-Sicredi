package com.beatriz.eventos.data.network.api

import com.beatriz.eventos.data.model.EventCheckinRequest

class ApiHelper(private val apiService: EventAPI) {

    suspend fun getAllEvents() = apiService.getAllEvents()

    suspend fun getEvent(id: Int) = apiService.getEvent(id)

    suspend fun checkIn(
        id: Int,
        name: String,
        email: String
    ) = apiService.checkIn(
        EventCheckinRequest(
            id,
            name,
            email
        )
    )
}
