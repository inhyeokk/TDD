package com.rkddlsgur983.test.model.kakao.web.response

import com.google.gson.annotations.SerializedName

data class KakaoWebResponse(
    @SerializedName("meta") val meta: KakaoWebMeta,
    @SerializedName("documents") val documentList: List<KakaoWebDocument>
)