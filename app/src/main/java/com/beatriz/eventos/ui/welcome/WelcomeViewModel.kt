package com.beatriz.eventos.ui.welcome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beatriz.eventos.data.model.User
import com.beatriz.eventos.utils.isValidEmail

class WelcomeViewModel(private val user: User) : ViewModel() {

    private val _btnEnabled = MutableLiveData(false)
    val btnEnabled = _btnEnabled

    private val _email = MutableLiveData<String>()
    val email = _email

    private val _name = MutableLiveData<String>()
    val name = _name

    fun setUser() {
        user.run {
            name = _name.value
            email = _email.value
        }
    }

    fun validateForms() {
        _btnEnabled.value =
            _email.value.orEmpty().isValidEmail() && name.value.orEmpty().isNotEmpty()
    }
}
