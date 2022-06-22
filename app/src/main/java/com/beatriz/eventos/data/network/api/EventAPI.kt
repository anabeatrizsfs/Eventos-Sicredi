package com.beatriz.eventos.data.network.api

import com.beatriz.eventos.data.model.EventCheckinRequest
import com.beatriz.eventos.data.model.EventResponse
import okhttp3.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventAPI {

    @GET("events")
    suspend fun getAllEvents(): List<EventResponse>

    @GET("events/{id}")
    suspend fun getEvent(@Path(value = "id") id: Int): EventResponse

    @POST("checkin")
    suspend fun checkin(@Body eventCheckinRequest: EventCheckinRequest): Call

}
