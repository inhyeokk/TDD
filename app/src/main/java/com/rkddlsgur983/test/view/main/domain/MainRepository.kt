package com.rkddlsgur983.test.view.main.domain

import com.rkddlsgur983.test.model.kakao.web.request.KakaoWebRequest
import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebResponse
import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import io.reactivex.Single

interface MainRepository {

    fun requestWeather(weatherRequest: WeatherRequest): Single<WeatherResponse>

    fun requestKakaoWeb(kakaoWebRequest: KakaoWebRequest): Single<KakaoWebResponse>
}