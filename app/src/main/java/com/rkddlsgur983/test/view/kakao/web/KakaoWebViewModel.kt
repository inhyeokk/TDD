package com.rkddlsgur983.test.view.kakao.web

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.model.kakao.web.request.KakaoWebRequest
import com.rkddlsgur983.test.model.kakao.web.request.KakaoWebSortType
import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebDocument
import com.rkddlsgur983.test.model.kakao.web.response.KakaoWebResponse
import com.rkddlsgur983.test.view.kakao.web.domain.KakaoWebRepository
import com.rkddlsgur983.test.view.kakao.web.entity.KakaoWebItem
import io.reactivex.android.schedulers.AndroidSchedulers

class KakaoWebViewModel(
    private val repo: KakaoWebRepository
): BaseViewModel() {

    private val TAG = KakaoWebViewModel::class.java.name
    val kakaoWebItemLiveData = MutableLiveData<MutableList<KakaoWebItem>>()

    val moveToExternalBrowserEvent = MutableLiveData<KakaoWebItem>()

    fun onClickWebItem(kakaoWebItem: KakaoWebItem) {
        moveToExternalBrowserEvent.postValue(kakaoWebItem)
    }

    fun requestKakaoWeb(query: String, size: Int) {

        val kakaoWebRequest = KakaoWebRequest(
            query = query,
            sort = KakaoWebSortType.ACCURACY,
            page = 1,
            size = size
        )
        repo.requestKakaoWeb(kakaoWebRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleKakaoWebResponse, this::handleError)
            .register()
    }

    private fun handleKakaoWebResponse(response: KakaoWebResponse) {

        val itemList = mutableListOf<KakaoWebItem>()
        for (document: KakaoWebDocument in response.documentList) {
            itemList.add(KakaoWebItem(
                title = document.title,
                url = document.url,
                contents = document.contents,
                dateTime = document.dateTime
            ))
        }
        kakaoWebItemLiveData.postValue(itemList)
    }

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}