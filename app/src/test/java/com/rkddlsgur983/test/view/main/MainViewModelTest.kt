package com.rkddlsgur983.test.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.util.BasicUtil
import com.rkddlsgur983.test.view.main.data.MainRepositoryImpl
import com.rkddlsgur983.test.view.main.entity.WeatherItem
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

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
        val weatherItemList = ArrayList<WeatherItem>()
        mainViewModel.weatherItemLiveData.observeForever {
            weatherItemList.addAll(it)
        }
        mainViewModel.requestWeather()

        // then
        assertEquals(true, weatherItemList.size == 7)
    }

    @Test
    fun `WeatherAPI 요청 지역이 모스크바인지 테스트`() {
        // when
        var weatherCityName = ""
        mainViewModel.weatherCityLiveData.observeForever {
            weatherCityName = it.name
        }
        mainViewModel.requestWeather()

        // then
        assertEquals("Moscow", weatherCityName)
    }

    @Test
    fun `날짜가 형식에 맞게 표시되는지 테스트`() {
        // when
        val date = BasicUtil.convertToDateFormat(1485766800)
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss", Locale.KOREA)
        val localDateTime = LocalDateTime.parse(date, dateTimeFormatter)
        val result = localDateTime.format(dateTimeFormatter)

        // then
        assertEquals(0, result.compareTo(date))
    }
}
