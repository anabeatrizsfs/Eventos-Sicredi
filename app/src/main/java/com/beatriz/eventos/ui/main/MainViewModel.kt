package com.beatriz.eventos.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beatriz.eventos.data.model.EventResponse
import com.beatriz.eventos.data.network.EventRepository
import com.beatriz.eventos.utils.Resource
import com.beatriz.eventos.utils.ResourceStatus

class MainViewModel(private val eventRepository: EventRepository) : ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _data = MediatorLiveData<List<EventResponse>?>().apply {
        addSource(eventRepository.eventsResourceState) {
            handleStates(it)
        }
    }
    val data: LiveData<List<EventResponse>?> = _data

    init {
        eventRepository.getAllEvents()
    }

    private fun handleStates(resource: Resource<List<EventResponse>>) {
        _loading.value = false
        when (resource.status) {
            ResourceStatus.SUCCESS -> resource.data?.run { _data.value = this }
            ResourceStatus.LOADING -> _loading.value = true
            ResourceStatus.ERROR -> _error.value = true
        }
    }
}
