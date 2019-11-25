package com.rkddlsgur983.test.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.util.BasicUtil
import com.rkddlsgur983.test.view.main.data.MainRepositoryImpl
import com.rkddlsgur983.test.view.main.entity.KakaoWebItem
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun init() {
        initMainViewModel()
    }

    private fun initMainViewModel() {
        mainViewModel = MainViewModel(MainRepositoryImpl())
    }

    @Test
    fun `WeatherAPI 수신 데이터가 7개인지 테스트`() {
        // when
        mainViewModel.requestWeather()

        // then
        mainViewModel.weatherItemLiveData.observeForever { weatherItemList ->
            assertEquals(weatherItemList.size, 7)
        }
    }

    @Test
    fun `WeatherAPI 요청 지역이 모스크바인지 테스트`() {
        // when
        mainViewModel.requestWeather()

        // then
        mainViewModel.weatherCityLiveData.observeForever { weatherCity ->
            assertEquals("Moscow", weatherCity.name)
        }
    }

    @Test
    fun `날짜가 형식에 맞게 표시되는지 테스트`() {
        // when
        val date = BasicUtil.convertToDateFormat(1485766800)
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss", Locale.KOREA)
        val localDateTime = LocalDateTime.parse(date, dateTimeFormatter)
        val result = localDateTime.format(dateTimeFormatter)

        // then
        assertEquals(result, date)
    }

    @Test
    fun `요청한 개수만큼 데이터를 수신하는지 테스트`() {
        // given
        val size = 10
        val kakaoWebImageList = createKakaoWebItemList(size)

        // when
        mainViewModel.requestKakaoWeb("테스트", size)

        // then
        mainViewModel.kakaoWebItemLiveData.observeForever {
            assertEquals(kakaoWebImageList.size, it.size)
        }
    }

    @Test
    fun `이미지 클릭 시 외부 브라우저 이동 이벤트가 호출되는지 테스트`() {
        // given
        val kakaoWebItem = createKakaoWebItem("0")

        // when
        mainViewModel.onClickWebItem(kakaoWebItem)

        // then
        mainViewModel.moveToExternalBrowserEvent.observeForever { receivedKakaoWebItem ->
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
