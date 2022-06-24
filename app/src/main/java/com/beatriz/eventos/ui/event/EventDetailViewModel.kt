package com.beatriz.eventos.ui.event

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.beatriz.eventos.data.network.EventRepository
import com.beatriz.eventos.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class EventDetailViewModel(private val eventRepository: EventRepository) : ViewModel() {

    val errorVisibility = MutableLiveData(false)

    fun getEvent(
        id: Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = eventRepository.getEvent(id)))
            Log.d("Event", "event ${this.latestValue}")
        } catch (exception: Exception) {
            // TODO mapear erros
            Log.d("Event", exception.message.orEmpty())
            errorVisibility.postValue(true)
            emit(Resource.error(data = null, message = exception.message ?: "Error!"))
        }
    }

    private val _checkinLiveData = MutableLiveData<Resource<Unit>>()
    val checkinLiveData = _checkinLiveData

    fun checkIn(
        id: Int,
        name: String,
        email: String
    ) {
        _checkinLiveData.value = Resource.loading(data = null)
        viewModelScope.launch {
            try {
                eventRepository.checkIn(
                    id,
                    name,
                    email
                )
                _checkinLiveData.value = Resource.success(Unit)
            } catch (exception: Exception) {
                // TODO mapear erros
                _checkinLiveData.value =
                    Resource.error(data = null, message = exception.message ?: "Error!")
            }
        }
    }
}
