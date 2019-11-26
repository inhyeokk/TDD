package com.rkddlsgur983.test.view.weather.domain

import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import io.reactivex.Single

interface WeatherRepository {
    fun requestWeather(weatherRequest: WeatherRequest): Single<WeatherResponse>
}