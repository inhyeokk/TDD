package com.rkddlsgur983.test.view.kakao.web.domain

import com.rkddlsgur983.test.model.kakao.web.request.KakaoWebRequest
import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebResponse
import io.reactivex.Single

interface KakaoWebRepository {
    fun requestKakaoWeb(kakaoWebRequest: KakaoWebRequest): Single<KakaoWebResponse>
}