package com.beatriz.eventos.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beatriz.eventos.data.model.User
import com.beatriz.eventos.utils.isValidEmail

class WelcomeViewModel(private val user: User) : ViewModel() {

    private val _btnEnabled = MutableLiveData(false)
    val btnEnabled: LiveData<Boolean> = _btnEnabled

    val email = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    fun setUser() {
        user.run {
            name = this@WelcomeViewModel.name.value
            email = this@WelcomeViewModel.email.value
        }
    }

    fun validateForms() {
        _btnEnabled.value =
            email.value.orEmpty().isValidEmail() && name.value.orEmpty().isNotEmpty()
    }
}
