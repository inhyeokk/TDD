package com.rkddlsgur983.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.view.main.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun init() {
        initMainViewModel()
    }

    private fun initMainViewModel() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun `Hello world!가 반환되는지 테스트`() {
        // when
        var text = ""
        mainViewModel.observeText().observeForever { text = it }
        mainViewModel.setText()

        // then
        assertEquals("Hello world!", text)
    }
}
