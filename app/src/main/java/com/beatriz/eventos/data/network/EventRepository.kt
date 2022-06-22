package com.beatriz.eventos.data.network

import com.beatriz.eventos.data.network.api.ApiHelper

class EventRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllEvents() = apiHelper.getAllEvents()
}
