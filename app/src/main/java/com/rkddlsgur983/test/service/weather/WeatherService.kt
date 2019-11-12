package com.rkddlsgur983.test.service.weather

import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecast/daily/")
    fun requestWeather(
        @Query(value = "id") id: String,
        @Query(value = "appid") appId: String
    ): Single<WeatherResponse>
}