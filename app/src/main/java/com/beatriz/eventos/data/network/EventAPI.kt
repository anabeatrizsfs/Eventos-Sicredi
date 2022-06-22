package com.beatriz.eventos.data.network

import com.beatriz.eventos.data.model.EventCheckinRequest
import com.beatriz.eventos.data.model.EventResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventAPI {

    @GET("/events")
    fun getAllEvents(): List<EventResponse>

    @GET("/events/{id}")
    fun getEvent(@Path(value = "id") id: Int): EventResponse

    @POST("/checkin")
    fun checkin(@Body eventCheckinRequest: EventCheckinRequest)

}
