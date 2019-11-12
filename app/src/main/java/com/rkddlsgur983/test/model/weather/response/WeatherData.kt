package com.rkddlsgur983.test.model.weather.response

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class WeatherData(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val weatherTemperature: WeatherTemperature,
    @SerializedName("pressure") val pressure: Float,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val detail: List<WeatherDetail>,
    @SerializedName("speed") val speed: Float,
    @SerializedName("deg") val degree: Float,
    @SerializedName("clouds") val clouds: Int,
    @SerializedName("snow") val snow: Float
)