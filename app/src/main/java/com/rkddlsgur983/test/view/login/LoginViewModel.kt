package com.rkddlsgur983.test.view.login

import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.view.login.extension.isValidEmail
import com.rkddlsgur983.test.view.login.entity.LoginType
import com.rkddlsgur983.test.view.login.extension.isValidPassword

class LoginViewModel: BaseViewModel() {

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginClickable = MutableLiveData<Boolean>()

    val loginClickEvent = MutableLiveData<LoginType>()
    val joinClickEvent = MutableLiveData<Boolean>()

    init {
        onUpdateId("")
        onUpdatePassword("")
    }

    fun onUpdateId(value: String) {
        id.value = value
        onUpdateLoginClickable()
    }

    fun onUpdatePassword(value: String) {
        password.value = value
        onUpdateLoginClickable()
    }

    private fun onUpdateLoginClickable() {
        loginClickable.value = !id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
    }

    fun onLoginClick() {
        loginClickEvent.value = checkLogin()
    }

    private fun checkLogin() = if (id.value.toString().isEmpty()) {
        LoginType.NONE_EMAIL
    } else if (password.value.toString().isEmpty()) {
        LoginType.NONE_PASSWORD
    } else if (!id.value.toString().isValidEmail()) {
        LoginType.INVALID_EMAIL
    } else if (!password.value.toString().isValidPassword()) {
        LoginType.INVALID_PASSWORD
    } else {
        LoginType.VALID
    }

    fun onJoinClick() {
        joinClickEvent.value = true
    }
}