package com.rkddlsgur983.test.view.hello

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class HelloViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockApplication: Application

    private lateinit var helloViewModel: HelloViewModel

    @Before
    fun init() {
        helloViewModel = HelloViewModel(mockApplication)
    }

    @Test
    fun `텍스트가 Hello world!로 변경되는지 테스트`() {
        // when
        helloViewModel.onHelloClick()

        // then
        helloViewModel.hello.observeForever {
            assertEquals("Hello world!", it)
        }
    }
}