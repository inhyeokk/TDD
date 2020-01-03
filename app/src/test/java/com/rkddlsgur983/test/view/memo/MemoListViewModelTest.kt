package com.rkddlsgur983.test.view.memo

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.model.memo.MemoDatabase
import com.rkddlsgur983.test.model.memo.data.MemoRepository
import com.rkddlsgur983.test.view.login.data.ApplicationDelegateImpl
import com.rkddlsgur983.test.view.memo.entity.MemoViewType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MemoListViewModelTest {

    companion object {
        private const val NONE_MEMO = "저장된 메모가 없습니다."
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockApplication: Application

    private lateinit var memoListViewModel: MemoListViewModel

    @Before
    fun init() {
        initString()
        initMemoListViewModel()
    }

    private fun initString() {
        `when`(mockApplication.getString(R.string.memo_list_toast_none)).thenReturn(NONE_MEMO)
    }

    private fun initMemoListViewModel() {
        memoListViewModel = MemoListViewModel(
            ApplicationDelegateImpl(mockApplication),
            MemoRepository(MemoDatabase.getDatabase(mockApplication))
        )
    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) x (Then) 저장된 메모가 없을 경우 토스트 출력 테스트`() {

        memoListViewModel.showMessageEvent.observeForever { message ->
            assertEquals(NONE_MEMO, message)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 데이터 불러옴 (When) x (Then) 저장된 메모가 한번에 20개씩 표시되는지 테스트`() {

        memoListViewModel.onLoadMemoFromDatabase()

    }

    @Test
    fun `(Given) 앱 실행 - 초기값 (When) 추가 버튼 클릭 (Then) 메모추가 화면으로 이동 테스트`() {

        memoListViewModel.moveViewEvent.observeForever { viewType ->
            assertEquals(MemoViewType.ADD, viewType)
        }
    }

    @Test
    fun `(Given) 앱 실행 - 데이터 불러옴 (When) 메모 항목 클릭 (Then) 메모상세 화면으로 이동 테스트`() {

        memoListViewModel.onMemoClick()

        memoListViewModel.moveViewEvent.observeForever { viewType ->
            assertEquals(MemoViewType.DETAIL, viewType)
        }
    }
}