package com.beatriz.eventos.data.network

import com.beatriz.eventos.data.model.EventCheckinRequest
import com.beatriz.eventos.data.network.api.ApiHelper

class EventRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllEvents() = apiHelper.getAllEvents()

    suspend fun getEvent(id: Int) = apiHelper.getEvent(id)

    suspend fun checkin(body: EventCheckinRequest) = apiHelper.checkin(body)
}
