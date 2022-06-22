package com.beatriz.eventos.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun a() {
        viewModelScope.launch {

        }
    }
}