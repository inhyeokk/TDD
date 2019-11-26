package com.rkddlsgur983.test.view.kakao.web.data

import com.rkddlsgur983.test.model.kakao.web.request.KakaoWebRequest
import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebResponse
import com.rkddlsgur983.test.service.kakao.KakaoService
import com.rkddlsgur983.test.service.retrofit.RetrofitManager
import com.rkddlsgur983.test.view.kakao.web.domain.KakaoWebRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class KakaoWebRepositoryImpl: KakaoWebRepository {

    override fun requestKakaoWeb(kakaoWebRequest: KakaoWebRequest): Single<KakaoWebResponse> {

        val kakaoService: KakaoService = RetrofitManager.getRetrofitService(KakaoService::class.java)
        return kakaoService
            .requestWeb(kakaoWebRequest.query, kakaoWebRequest.sort.name, kakaoWebRequest.page, kakaoWebRequest.size)
            .subscribeOn(Schedulers.io())
    }
}