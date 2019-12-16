package com.rkddlsgur983.test.view.memo

import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.view.memo.entity.MemoCategory
import com.rkddlsgur983.test.view.memo.entity.MemoViewType

class MemoAddViewModel: BaseViewModel() {

    val title = MutableLiveData<String>()
    val category = MutableLiveData<MemoCategory>()
    val contents = MutableLiveData<String>()
    val completeClickable = MutableLiveData<Boolean>()

    val moveViewEvent = MutableLiveData<MemoViewType>()

    init {
        onUpdateTitle("")
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
    }

    /* 제목은 공백으로 저장될 수 없음
     * 카테고리는 반드시 한가지가 선택 되어야 함
     * 입력 조건에 의하여 완료 버튼은 활성화 / 비활성화 됨
     */
    private fun onUpdateCompleteClickable() {
        completeClickable.value = !title.value.isNullOrEmpty()
    }

    private fun onUpdateMoveView(viewType: MemoViewType) {
        moveViewEvent.value = viewType
    }

    fun onCompleteClick() {
        onUpdateMoveView(MemoViewType.LIST)
    }
}