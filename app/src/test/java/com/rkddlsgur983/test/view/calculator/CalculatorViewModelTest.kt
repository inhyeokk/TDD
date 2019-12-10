package com.rkddlsgur983.test.view.calculator

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.view.calculator.entity.OperatorType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*

class CalculatorViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var calculatorViewModel: CalculatorViewModel

    @Before
    fun init() {
        initCalculatorViewModel()
    }

    private fun initCalculatorViewModel() {
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun `더하기 버튼 선택했을 때 두 값이 더해지는지 테스트`() {
        // when
        calculatorViewModel.onOperatorClick(OperatorType.PLUS)

        // then
        calculatorViewModel.resultText.observeForever { result ->
            assertEquals(result, "10")
        }
    }

    @Test
    fun `곱하기 버튼 선택했을 때 두 값이 곱해지는지 테스트`() {
        // when
        calculatorViewModel.onOperatorClick(OperatorType.MULTIPLE)

        // then
        calculatorViewModel.resultText.observeForever { result ->
            assertEquals(result, "21")
        }
    }
}