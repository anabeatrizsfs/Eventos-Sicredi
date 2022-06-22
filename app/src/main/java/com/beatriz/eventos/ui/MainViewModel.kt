package com.beatriz.eventos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.beatriz.eventos.data.network.EventRepository
import com.beatriz.eventos.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val eventRepository: EventRepository) : ViewModel() {

    fun getAllEvents() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = eventRepository.getAllEvents()))
        } catch (exception: Exception) {
            // TODO mapear erros
            emit(Resource.error(data = null, message = exception.message ?: "Error!"))
        }
    }
}
