package com.rkddlsgur983.test.view.kakao.web

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.view.kakao.web.data.KakaoWebRepositoryImpl
import com.rkddlsgur983.test.view.kakao.web.entity.KakaoWebItem
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

class KakaoWebViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var mockApplication: Application

    private lateinit var kakaoWebViewModel: KakaoWebViewModel

    @Before
    fun init() {
        initKakaoWebViewModel()
    }

    private fun initKakaoWebViewModel() {
        kakaoWebViewModel = KakaoWebViewModel(mockApplication, KakaoWebRepositoryImpl())
    }

    @Test
    fun `요청한 개수만큼 데이터를 수신하는지 테스트`() {
        // given
        val size = 10
        val kakaoWebImageList = createKakaoWebItemList(size)

        // when
        kakaoWebViewModel.requestKakaoWeb("테스트", size)

        // then
        kakaoWebViewModel.kakaoWebItemLiveData.observeForever {
            assertEquals(kakaoWebImageList.size, it.size)
        }
    }

    @Test
    fun `이미지 클릭 시 외부 브라우저 이동 이벤트가 호출되는지 테스트`() {
        // given
        val kakaoWebItem = createKakaoWebItem("0")

        // when
        kakaoWebViewModel.onClickWebItem(kakaoWebItem)

        // then
        kakaoWebViewModel.moveToExternalBrowserEvent.observeForever { receivedKakaoWebItem ->
            assertEquals(kakaoWebItem, receivedKakaoWebItem)
        }
    }

    private fun createKakaoWebItemList(size: Int): MutableList<KakaoWebItem> {
        val list = mutableListOf<KakaoWebItem>()
        for (i in 0 until size) {
            list.add(createKakaoWebItem(i.toString()))
        }
        return list
    }

    private fun createKakaoWebItem(id: String) = KakaoWebItem(
        dateTime = "2019.11.25 14:51:00",
        title = "안드테스트",
        contents = "no.$id",
        url = "https://github.com/rkddlsgur983"
    )
}
