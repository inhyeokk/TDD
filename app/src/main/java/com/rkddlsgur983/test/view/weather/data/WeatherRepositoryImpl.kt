package com.rkddlsgur983.test.view.weather.data

import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import com.rkddlsgur983.test.service.retrofit.RetrofitManager
import com.rkddlsgur983.test.service.weather.WeatherService
import com.rkddlsgur983.test.view.weather.domain.WeatherRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class WeatherRepositoryImpl: WeatherRepository {

    override fun requestWeather(weatherRequest: WeatherRequest): Single<WeatherResponse> {

        val weatherService: WeatherService = RetrofitManager.getRetrofitService(WeatherService::class.java)
        return weatherService
            .requestWeather(weatherRequest.id, weatherRequest.appId)
            .subscribeOn(Schedulers.io())
    }
}