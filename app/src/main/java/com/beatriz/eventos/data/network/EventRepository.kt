package com.beatriz.eventos.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beatriz.eventos.data.model.EventResponse
import com.beatriz.eventos.data.network.api.RemoteDataSource
import com.beatriz.eventos.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class EventRepository(
    private val remoteDataSource: RemoteDataSource,
    coroutineDispatcher: CoroutineDispatcher
) {
    private val scope = CoroutineScope(coroutineDispatcher)

    private val _eventsResourceState = MutableLiveData<Resource<List<EventResponse>>>()
    val eventsResourceState: LiveData<Resource<List<EventResponse>>> = _eventsResourceState

    fun getAllEvents() {
        _eventsResourceState.postValue(Resource.loading())
        scope.launch {
            try {
                _eventsResourceState.postValue(Resource.success(remoteDataSource.getAllEvents()))
            } catch (e: Exception) {
                _eventsResourceState.postValue(Resource.error(message = e.message ?: "Error!"))
            }
        }
    }

    private val _eventResourceState = MutableLiveData<Resource<EventResponse>>()
    val eventResourceState: LiveData<Resource<EventResponse>> = _eventResourceState

    fun getEvent(id: Int) {
        _eventResourceState.postValue(Resource.loading())
        scope.launch {
            try {
                _eventResourceState.postValue(Resource.success(remoteDataSource.getEvent(id)))
            } catch (e: Exception) {
                _eventResourceState.postValue(Resource.error(message = e.message ?: "Error!"))
            }
        }
    }

    private val _checkinResourceState = MutableLiveData<Resource<Response<Void>>>()
    val checkinResourceState: LiveData<Resource<Response<Void>>> = _checkinResourceState

    fun checkIn(
        id: Int,
        name: String,
        email: String
    ) {
        _checkinResourceState.postValue(Resource.loading())
        scope.launch {
            try {
                _checkinResourceState.postValue(
                    Resource.success(
                        remoteDataSource.checkIn(
                            id,
                            name,
                            email
                        )
                    )
                )
            } catch (e: HttpException) {
                _checkinResourceState.postValue(Resource.error(message = e.message ?: "Error!"))
            }
        }
    }
}
