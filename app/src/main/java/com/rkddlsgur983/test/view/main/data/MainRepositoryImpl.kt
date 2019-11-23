package com.rkddlsgur983.test.view.main.data

import com.rkddlsgur983.test.model.kakao.web.request.KakaoWebRequest
import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebResponse
import com.rkddlsgur983.test.model.weather.request.WeatherRequest
import com.rkddlsgur983.test.model.weather.response.WeatherResponse
import com.rkddlsgur983.test.service.kakao.KakaoService
import com.rkddlsgur983.test.service.retrofit.RetrofitManager
import com.rkddlsgur983.test.service.weather.WeatherService
import com.rkddlsgur983.test.util.BasicUtil
import com.rkddlsgur983.test.view.main.domain.MainRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl: MainRepository {

    override fun requestWeather(weatherRequest: WeatherRequest): Single<WeatherResponse> {

        val weatherService: WeatherService = RetrofitManager.getRetrofitService(WeatherService::class.java)
        return weatherService
            .requestWeather(weatherRequest.id, weatherRequest.appId)
            .subscribeOn(Schedulers.io())
    }

    override fun requestKakaoWeb(kakaoWebRequest: KakaoWebRequest): Single<KakaoWebResponse> {

        val kakaoService: KakaoService = RetrofitManager.getRetrofitService(KakaoService::class.java)
        return kakaoService
            .requestWeb(kakaoWebRequest.query, kakaoWebRequest.sort, kakaoWebRequest.page, kakaoWebRequest.size)
            .subscribeOn(Schedulers.io())
    }
}