package com.rkddlsgur983.test.model.kakao.web.response

import com.google.gson.annotations.SerializedName

data class KakaoWebMeta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)