package com.rkddlsgur983.test.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherCity
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import com.rkddlsgur983.test.util.BasicUtil
import com.rkddlsgur983.test.view.main.domain.MainRepository
import com.rkddlsgur983.test.view.main.entity.WeatherItem
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel(private val repo: MainRepository): BaseViewModel() {

    private val TAG = MainViewModel::class.java.name
    private val weatherItemLiveData = MutableLiveData<ArrayList<WeatherItem>>()
    private val weatherCityLiveData = MutableLiveData<WeatherCity>()

    fun observeWeatherItem() = weatherItemLiveData
    fun observeWeatherCity() = weatherCityLiveData

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

        val items = ArrayList<WeatherItem>()
        for (weather in response.weatherDataList) {
            items.add(WeatherItem(
                date = BasicUtil.convertToDateFormat(weather.date),
                main = weather.detail[0].main,
                description = weather.detail[0].description
            ))
        }
        weatherItemLiveData.postValue(items)
        weatherCityLiveData.postValue(response.weatherCity)
    }

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}