package com.rkddlsgur983.test.view.login

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.view.login.extension.isValidEmail
import com.rkddlsgur983.test.view.login.entity.ViewType
import com.rkddlsgur983.test.view.login.extension.isValidPassword

class LoginViewModel(application: Application): BaseViewModel(application) {

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginClickable = MutableLiveData<Boolean>()

    val showMessageEvent = MutableLiveData<String>()
    val moveViewEvent = MutableLiveData<ViewType>()

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

    /* 이메일, 비밀번호가 입력되어 있으면 버튼 활성화 됨
     */
    private fun onUpdateLoginClickable() {
        loginClickable.value = !id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
    }

    private fun onUpdateShowMessage(@StringRes stringResId: Int) {
        showMessageEvent.value = getApplication<Application>().getString(stringResId)
    }

    private fun onUpdateMoveView(viewType: ViewType) {
        moveViewEvent.value = viewType
    }

    /* 로그인 버튼 클릭 시
     * - ID가 이메일 형식이 아닐 경우 - 토스트 표시
     * - 비밀번호가 5자 이하일 경우 - 토스트 표시
     * - 모든 입력 조건을 만족할 경우 - TBD
     */
    fun onLoginClick() {
        if (!id.value.toString().isValidEmail()) {
            onUpdateShowMessage(R.string.login_toast_invalid_email)
        } else if (!password.value.toString().isValidPassword()) {
            onUpdateShowMessage(R.string.login_toast_invalid_password)
        } else {
            onUpdateMoveView(ViewType.LOGIN)
        }
    }

    /* 회원가입 버튼 클릭 시 - TBD
     */
    fun onJoinClick() {
        onUpdateMoveView(ViewType.JOIN)
    }
}