package com.rkddlsgur983.test.view.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherCity
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import com.rkddlsgur983.test.util.BasicUtils
import com.rkddlsgur983.test.view.weather.domain.WeatherRepository
import com.rkddlsgur983.test.view.weather.entity.WeatherItem
import io.reactivex.android.schedulers.AndroidSchedulers

class WeatherViewModel(private val repo: WeatherRepository):BaseViewModel() {

    private val TAG = "WEATHER_VIEW_MODEL"
    val weatherItemLiveData = MutableLiveData<MutableList<WeatherItem>>()
    val weatherCityLiveData = MutableLiveData<WeatherCity>()

    fun requestWeather() {

        val weatherRequest = WeatherRequest(
            id = "524901",
            appId = "b1b15e88fa797225412429c1c50c122a1"
        )
        registerDisposable(
            repo.requestWeather(weatherRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWeatherResponse, this::handleError)
        )
    }

    private fun handleWeatherResponse(response: WeatherResponse) {

        val items = mutableListOf<WeatherItem>()
        for (weather in response.weatherDataList) {
            items.add(
                WeatherItem(
                    date = BasicUtils.convertToDateFormat(weather.date),
                    main = weather.detail[0].main,
                    description = weather.detail[0].description
                )
            )
        }
        weatherItemLiveData.value = items
        weatherCityLiveData.value = response.weatherCity
    }

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}