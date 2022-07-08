package com.beatriz.eventos.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beatriz.eventos.data.model.EventResponse
import com.beatriz.eventos.data.model.User
import com.beatriz.eventos.data.network.EventRepository
import com.beatriz.eventos.utils.Resource
import com.beatriz.eventos.utils.ResourceStatus
import retrofit2.Response

class EventDetailViewModel(
    private val eventRepository: EventRepository,
    private val user: User
) : ViewModel() {

    // Event
    private val _loadingEvent = MutableLiveData(false)
    val loadingEvent: LiveData<Boolean> = _loadingEvent

    private val _errorEvent = MutableLiveData(false)
    val errorEvent: LiveData<Boolean> = _errorEvent

    private val _dataEvent = MediatorLiveData<EventResponse>().apply {
        addSource(eventRepository.eventResourceState) {
            handleStateEvent(it)
        }
    }

    val dataEvent: LiveData<EventResponse> = _dataEvent

    fun getEvent(id: Int) {
        checkinId = id.apply {
            eventRepository.getEvent(this)
        }
    }

    private fun handleStateEvent(resource: Resource<EventResponse>) {
        _loadingEvent.value = false
        when (resource.status) {
            ResourceStatus.SUCCESS -> resource.data?.run { _dataEvent.value = this }
            ResourceStatus.LOADING -> _loadingEvent.value = true
            ResourceStatus.ERROR -> _errorEvent.value = true
        }
    }

    // CheckIn
    private val _errorCheckin = MutableLiveData<Unit>()
    val errorCheckin: LiveData<Unit> = _errorCheckin

    private val _checkinLiveData = MediatorLiveData<Unit>().apply {
        addSource(eventRepository.checkinResourceState) {
            handleStateCheckIn(it)
        }
    }
    val checkinLiveData: LiveData<Unit> = _checkinLiveData

    private val _loadingCheckin = MutableLiveData(false)
    val loadingCheckin: LiveData<Boolean> = _loadingCheckin

    private var checkinId: Int? = null

    fun checkIn() {
        checkinId?.let {
            user.run {
                eventRepository.checkIn(
                    id = it,
                    name = name.orEmpty(),
                    email = email.orEmpty()
                )
            }
        }
    }

    private fun handleStateCheckIn(resource: Resource<Response<Void>>) {
        _loadingCheckin.value = false
        when (resource.status) {
            ResourceStatus.SUCCESS -> resource.data?.run { _checkinLiveData.value = Unit }
            ResourceStatus.LOADING -> _loadingCheckin.value = true
            ResourceStatus.ERROR -> _errorCheckin.value = Unit
        }
    }
}
