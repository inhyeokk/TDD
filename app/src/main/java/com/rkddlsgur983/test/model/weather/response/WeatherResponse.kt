package com.rkddlsgur983.test.model.weather.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod") val code: String,
    @SerializedName("message") val message: Int,
    @SerializedName("city") val weatherCity: WeatherCity,
    @SerializedName("cnt") val count: Int,
    @SerializedName("list") val weatherDataList: List<WeatherData>
)