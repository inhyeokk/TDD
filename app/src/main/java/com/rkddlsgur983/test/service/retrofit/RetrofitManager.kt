package com.rkddlsgur983.test.service.retrofit

import com.rkddlsgur983.test.service.kakao.KakaoConst
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private const val ALL_TIMEOUT = 10L

    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit

    init {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BASIC

        okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLogging)
            addInterceptor(HeaderSettingInterceptor())
            connectTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
        }.build()

        retrofit = Retrofit.Builder().apply {
            baseUrl(KakaoConst.URL)
            client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    private class HeaderSettingInterceptor: Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            val chainRequest = chain.request()
            val request = chainRequest.newBuilder().apply{
                addHeader("Content-Type", "application/x-www-form-urlencoded")
                addHeader("Authorization", "KakaoAK " + KakaoConst.KEY)
            }.build()
            return chain.proceed(request)
        }
    }

    internal fun <T> getRetrofitService(restClass: Class<T>): T {
        return retrofit.create(restClass)
    }
}