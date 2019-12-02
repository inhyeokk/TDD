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
    fun `(Given) 앱 실행 - 초기값 (When) x (Then) 버튼 비활성화 테스트`() {

        loginViewModel.loginClickable.observeForever {
            assertEquals(false, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) 로그인 버튼 클릭 (Then) 이메일 입력 토스트 출력 테스트`() {

        loginViewModel.onLoginClick()

        loginViewModel.loginClickEvent.observeForever {
            assertEquals(LoginType.NONE_EMAIL, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일 입력 (When) x (Then) 버튼 비활성화 테스트`() {

        loginViewModel.onUpdateId("a@b.com")

        loginViewModel.loginClickable.observeForever {
            assertEquals(false, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일 입력 (When) 로그인 버튼 클릭 (Then) 비밀번호 입력 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("a@b.com")

        loginViewModel.onLoginClick()

        loginViewModel.loginClickEvent.observeForever {
            assertEquals(LoginType.NONE_PASSWORD, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 잘못된 이메일, 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) 유효하지 않은 이메일 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("abc")
        loginViewModel.onUpdatePassword("111111")

        loginViewModel.onLoginClick()

        loginViewModel.loginClickEvent.observeForever {
            assertEquals(LoginType.INVALID_EMAIL, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일, 짧은 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) 비밀번호 길이 토스트 출력 테스트`() {

        loginViewModel.onUpdateId("a@b.com")
        loginViewModel.onUpdatePassword("1111")

        loginViewModel.loginClickable.observeForever {
            assertEquals(true, it)
            when (it) {
                true -> loginViewModel.onLoginClick()
            }
        }

        loginViewModel.loginClickEvent.observeForever { loginType ->
            assertEquals(LoginType.INVALID_PASSWORD, loginType)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 이메일, 비밀번호 입력 (When) 로그인 버튼 클릭 (Then) TBD`() {

        loginViewModel.onUpdateId("a@b.com")
        loginViewModel.onUpdatePassword("111111")

        loginViewModel.loginClickable.observeForever {
            assertEquals(true, it)
            when (it) {
                true -> loginViewModel.onLoginClick()
            }
        }

        loginViewModel.loginClickEvent.observeForever { loginType ->
            assertEquals(LoginType.VALID, loginType)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) 회원가입 버튼 클릭 (Then) TBD`() {

        loginViewModel.onJoinClick()

        loginViewModel.joinClickEvent.observeForever {
            assertEquals(true, it)
        }
    }
}