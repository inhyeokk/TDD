package com.rkddlsgur983.test.model.kakao.web.response

import com.google.gson.annotations.SerializedName

data class KakaoWebDocument(
    @SerializedName("title") val title: String,
    @SerializedName("contents") val contents: String,
    @SerializedName("url") val url: String,
    @SerializedName("datetime") val dateTime: String
)