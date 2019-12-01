package com.rkddlsgur983.test.view.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.view.login.entity.LoginType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*

class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun init() {
        initLoginViewModel()
    }

    private fun initLoginViewModel() {
        loginViewModel = LoginViewModel()
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) 로그인 버튼 클릭 (Then) 이메일 형식 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("")
        loginViewModel.onUpdatePassword("")

        loginViewModel.onLoginClick()

        loginViewModel.loginClickEvent.observeForever { loginType ->
            assertEquals(LoginType.INVALID_EMAIL, loginType)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일, 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) 비밀번호 길이 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("a@b.com")
        loginViewModel.onUpdatePassword("1111")

        loginViewModel.onLoginClick()

        loginViewModel.loginClickEvent.observeForever { loginType ->
            assertEquals(LoginType.INVALID_PASSWORD, loginType)
        }
    }
}