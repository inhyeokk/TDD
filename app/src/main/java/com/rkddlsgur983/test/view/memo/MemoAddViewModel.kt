package com.rkddlsgur983.test.view.memo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.model.memo.data.MemoRepository
import com.rkddlsgur983.test.model.memo.entity.Memo
import com.rkddlsgur983.test.util.BasicUtils
import com.rkddlsgur983.test.view.memo.entity.MemoCategory
import com.rkddlsgur983.test.view.memo.entity.MemoViewType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MemoAddViewModel(
    private val memoRepository: MemoRepository
): BaseViewModel() {

    companion object {
        private val TAG = MemoAddViewModel::class.java.name
    }

    val title = MutableLiveData<String>()
    val category = MutableLiveData<MemoCategory>()
    val contents = MutableLiveData<String>()
    val contentsLength = MutableLiveData<Int>()
    val completeClickable = MutableLiveData<Boolean>()

    val moveViewEvent = MutableLiveData<MemoViewType>()

    init {
        onUpdateTitle("")
        onUpdateCategory(MemoCategory.PLEASE_CLICK)
        onUpdateContents("")
    }

    fun onUpdateTitle(value: String) {
        title.value = value
        onUpdateCompleteClickable()
    }

    fun onUpdateCategory(value: MemoCategory) {
        category.value = value
        onUpdateCompleteClickable()
    }

    fun onUpdateContents(value: String) {
        contents.value = value
        onUpdateContentsLength(value.length)
    }

    private fun onUpdateContentsLength(value: Int) {
        contentsLength.value = value
    }

    /* 제목은 공백으로 저장될 수 없음
     * 카테고리는 반드시 한가지가 선택 되어야 함
     * 입력 조건에 의하여 완료 버튼은 활성화 / 비활성화 됨
     */
    private fun onUpdateCompleteClickable() {
        completeClickable.value = !title.value.isNullOrEmpty() && category.value != MemoCategory.PLEASE_CLICK
    }

    private fun onUpdateMoveView(viewType: MemoViewType) {
        moveViewEvent.value = viewType
    }

    fun onCompleteClick() {
        insertMemo()
    }

    private fun insertMemo() {
        memoRepository.insert(Memo(0, title.value!!, category.value!!, contents.value!!, BasicUtils.getTime()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onUpdateMoveView(MemoViewType.LIST)
            }, this::handleError).register()
    }

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}