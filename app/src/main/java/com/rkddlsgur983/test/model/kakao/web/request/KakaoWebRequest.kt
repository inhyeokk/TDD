package com.rkddlsgur983.test.model.kakao.web.request

data class KakaoWebRequest(
    val query: String,
    val sort: KakaoWebSortType,
    val page: Int,
    val size: Int
)