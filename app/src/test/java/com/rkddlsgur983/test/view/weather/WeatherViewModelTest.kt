package com.rkddlsgur983.test.view.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rkddlsgur983.test.RxSchedulerRule
import com.rkddlsgur983.test.util.BasicUtil
import com.rkddlsgur983.test.view.weather.data.WeatherRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class WeatherViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun init() {
        initWeatherViewModel()
    }

    private fun initWeatherViewModel() {
        weatherViewModel = WeatherViewModel(WeatherRepositoryImpl())
    }

    @Test
    fun `WeatherAPI 수신 데이터가 7개인지 테스트`() {
        // when
        weatherViewModel.requestWeather()

        // then
        weatherViewModel.weatherItemLiveData.observeForever { weatherItemList ->
            assertEquals(weatherItemList.size,7)
        }
    }

    @Test
    fun `WeatherAPI 요청 지역이 모스크바인지 테스트`() {
        // when
        weatherViewModel.weatherCityLiveData.observeForever { weatherCity ->
            assertEquals(weatherCity.name, "Moscow")
        }

        // then
        weatherViewModel.requestWeather()
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
}