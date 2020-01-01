package com.rkddlsgur983.test.view.memo

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.view.login.data.ApplicationDelegateImpl
import com.rkddlsgur983.test.view.memo.entity.MemoCategory
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MemoAddViewModelTest {

    companion object {
        private const val CONTENTS_LENGTH = "(7 / 300)"
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockApplication: Application

    private lateinit var memoAddViewModel: MemoAddViewModel

    @Before
    fun init() {
        initViewModel()
    }

    private fun initViewModel() {
        memoAddViewModel = MemoAddViewModel(ApplicationDelegateImpl(mockApplication))
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

    @Test
    fun `(Given) 앱 실행 - 상세내용 입력 (When) x (Then) 입력 길이를 화면에 표시 테스트`() {

        memoAddViewModel.onUpdateContents("상세내용 입력")

        memoAddViewModel.contentsLength.observeForever { contentsLength ->
            assertEquals(CONTENTS_LENGTH, contentsLength)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 상세내용 입력 (When) x (Then) 입력 길이 최대 300글자 테스트`() {

        memoAddViewModel.onUpdateContents("300자")

        memoAddViewModel.contents.observeForever { contents ->
            assertEquals(true, contents.length <= 300)
        }
    }
}