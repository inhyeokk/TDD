package com.rkddlsgur983.test.view.login

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.view.login.entity.ViewType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    companion object {
        private const val INVALID_EMAIL = "잘못된 형식입니다."
        private const val INVALID_PASSWORD = "비밀번호는 최소 6자리 입력해주세요."
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var mockApplication: Application

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun init() {
        initString()
        initLoginViewModel()
    }

    private fun initString() {
        `when`(mockApplication.getString(R.string.login_toast_invalid_email)).thenReturn(INVALID_EMAIL)
        `when`(mockApplication.getString(R.string.login_toast_invalid_password)).thenReturn(INVALID_PASSWORD)
    }

    private fun initLoginViewModel() {
        loginViewModel = LoginViewModel(mockApplication)
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) x (Then) 버튼 비활성화 테스트`() {

        loginViewModel.loginClickable.observeForever { clickable ->
            assertEquals(false, clickable)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일 입력 (When) x (Then) 버튼 비활성화 테스트`() {

        loginViewModel.onUpdateId("a@b.com")

        loginViewModel.loginClickable.observeForever { clickable ->
            assertEquals(false, clickable)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 잘못된 이메일, 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) 유효하지 않은 이메일 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("abc")
        loginViewModel.onUpdatePassword("111111")

        loginViewModel.loginClickable.observeForever { clickable ->
            assertEquals(true, clickable)
            when (clickable) {
                true -> loginViewModel.onLoginClick()
            }
        }

        loginViewModel.showMessageEvent.observeForever { message ->
            assertEquals(INVALID_EMAIL, message)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일, 짧은 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) 비밀번호 길이 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("a@b.com")
        loginViewModel.onUpdatePassword("1111")

        loginViewModel.loginClickable.observeForever { clickable ->
            assertEquals(true, clickable)
            when (clickable) {
                true -> loginViewModel.onLoginClick()
            }
        }

        loginViewModel.showMessageEvent.observeForever {
            assertEquals(INVALID_PASSWORD, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일, 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) TBD`() {

        loginViewModel.onUpdateId("a@b.com")
        loginViewModel.onUpdatePassword("111111")

        loginViewModel.loginClickable.observeForever { clickable ->
            assertEquals(true, clickable)
            when (clickable) {
                true -> loginViewModel.onLoginClick()
            }
        }

        loginViewModel.moveViewEvent.observeForever { viewType ->
            assertEquals(ViewType.LOGIN, viewType)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) 회원가입 버튼 클릭 (Then) TBD`() {

        loginViewModel.onJoinClick()

        loginViewModel.moveViewEvent.observeForever { viewType ->
            assertEquals(ViewType.JOIN, viewType)
        }
    }
}