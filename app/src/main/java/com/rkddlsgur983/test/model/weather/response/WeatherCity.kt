package com.rkddlsgur983.test.model.weather.response

import com.google.gson.annotations.SerializedName

data class WeatherCity(
    @SerializedName("geoname_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lon") val longitude: Float,
    @SerializedName("country") val country: String,
    @SerializedName("iso2") val iso2: String,
    @SerializedName("type") val type: String,
    @SerializedName("population") val population: Int
)