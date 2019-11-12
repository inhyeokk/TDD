package com.rkddlsgur983.test.model.weather.response

import com.google.gson.annotations.SerializedName

data class WeatherTemperature(
    @SerializedName("day") val day: Float,
    @SerializedName("min") val min: Float,
    @SerializedName("max") val max: Float,
    @SerializedName("night") val night: Float,
    @SerializedName("eve") val eve: Float,
    @SerializedName("morn") val morning: Float
)