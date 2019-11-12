package com.rkddlsgur983.test.view.main.domain

import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import io.reactivex.Single

interface MainRepository {
    fun requestWeather(weatherRequest: WeatherRequest): Single<WeatherResponse>
}