package com.rkddlsgur983.test.view.memo

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.view.login.domain.ApplicationDelegate
import com.rkddlsgur983.test.view.memo.entity.MemoItem
import com.rkddlsgur983.test.view.memo.entity.MemoViewType

class MemoListViewModel(private val applicationDelegate: ApplicationDelegate): BaseViewModel() {

    val memoItemList = MutableLiveData<MutableList<MemoItem>>()

    val showMessageEvent = MutableLiveData<String>()
    val moveViewEvent = MutableLiveData<MemoViewType>()

    init {
        onLoadMemoFromDatabase()
    }

    private fun onUpdateShowMessage(@StringRes stringResId: Int) {
        showMessageEvent.value = applicationDelegate.getString(stringResId)
    }

    private fun onUpdateMoveView(viewType: MemoViewType) {
        moveViewEvent.value = viewType
    }

    fun onLoadMemoFromDatabase() {
        when (memoItemList.value!!.size) {
            0 -> onUpdateShowMessage(R.string.memo_list_toast_none)
            in 1..20 -> {
                // do nothing
            }
            else -> {
                // error
            }
        }
    }

    fun onAddClick() {
        onUpdateMoveView(MemoViewType.ADD)
    }

    fun onMemoClick() {
        onUpdateMoveView(MemoViewType.DETAIL)
    }
}