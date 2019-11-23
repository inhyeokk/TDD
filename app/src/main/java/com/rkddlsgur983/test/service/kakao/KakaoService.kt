package com.rkddlsgur983.test.service.kakao

import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {

    @GET("/v2/search/web")
    fun requestWeb(
        @Query(value = "query") query: String,
        @Query(value = "sort") sort: String,
        @Query(value = "page") page: Int,
        @Query(value = "size") size: Int
    ): Single<KakaoWebResponse>
}