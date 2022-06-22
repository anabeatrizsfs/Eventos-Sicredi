package com.beatriz.eventos.data.network.api

class ApiHelper(private val apiService: EventAPI) {

    suspend fun getAllEvents() = apiService.getAllEvents()
}
