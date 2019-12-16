package com.rkddlsgur983.test.view.memo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.view.memo.entity.MemoCategory
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*

class MemoAddViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var memoAddViewModel: MemoAddViewModel

    @Before
    fun init() {
        initViewModel()
    }

    private fun initViewModel() {
        memoAddViewModel = MemoAddViewModel()
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) x (Then) 완료 버튼 비활성화 테스트`() {

        memoAddViewModel.completeClickable.observeForever { clickable ->
            assertEquals(false, clickable)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 제목 입력 (When) x (Then) 완료 버튼 비활성화 테스트`() {

        memoAddViewModel.onUpdateTitle("제목")

        memoAddViewModel.completeClickable.observeForever { clickable ->
            assertEquals(false, clickable)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 카테고리 선택 (When) x (Then) 완료 버튼 비활성화 테스트`() {

        memoAddViewModel.onUpdateCategory(MemoCategory.WORK_TO_DO)

        memoAddViewModel.completeClickable.observeForever { clickable ->
            assertEquals(false, clickable)
        }
    }
}