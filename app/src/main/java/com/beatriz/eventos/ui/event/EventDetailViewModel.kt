package com.beatriz.eventos.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.beatriz.eventos.data.model.User
import com.beatriz.eventos.data.network.EventRepository
import com.beatriz.eventos.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val eventRepository: EventRepository,
    private val user: User
) : ViewModel() {

    private val _checkinLiveData = MutableLiveData<Resource<Unit>>()
    val checkinLiveData = _checkinLiveData

    private var checkinId: Int? = null

    fun getEvent(
        id: Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            eventRepository.getEvent(id).apply {
                checkinId = id
                emit(Resource.success(data = this))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error!"))
        }
    }

    fun checkIn() {
        _checkinLiveData.value = Resource.loading(data = null)
        viewModelScope.launch {
            try {
                checkinId?.let {
                    user.run {
                        eventRepository.checkIn(
                            id = it,
                            name = name.orEmpty(),
                            email = email.orEmpty()
                        )
                    }
                }
                _checkinLiveData.value = Resource.success(Unit)
            } catch (exception: Exception) {
                _checkinLiveData.value =
                    Resource.error(data = null, message = exception.message ?: "Error!")
            }
        }
    }
}
