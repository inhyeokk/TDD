package com.rkddlsgur983.test.view.memo

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.model.memo.data.MemoRepository
import com.rkddlsgur983.test.view.login.domain.ApplicationDelegate
import com.rkddlsgur983.test.view.memo.entity.MemoItem
import com.rkddlsgur983.test.view.memo.entity.MemoViewType

class MemoListViewModel(
    private val applicationDelegate: ApplicationDelegate,
    private val memoRepository: MemoRepository
): BaseViewModel() {

    val memoItemListLiveData: LiveData<MutableList<MemoItem>> = Transformations.map(memoRepository.getMemoList()) { memoList ->
        val memoItemList = mutableListOf<MemoItem>()
        memoList.forEach { memo ->
            memoItemList.add(MemoItem(memo.title, memo.category, memo.contents, memo.date))
        }
        memoItemList
    }

    val showMessageEvent: LiveData<String> = Transformations.map(memoItemListLiveData) {
        if (it.isEmpty()) {
            onUpdateShowMessage(R.string.memo_list_toast_none)
        } else {
            ""
        }
    }

    val moveViewEvent = MutableLiveData<MemoViewType>()

    init {
        onLoadMemoFromDatabase()
    }

    private fun onUpdateShowMessage(@StringRes stringResId: Int) = applicationDelegate.getString(stringResId)

    private fun onUpdateMoveView(viewType: MemoViewType) {
        moveViewEvent.value = viewType
    }

    fun onLoadMemoFromDatabase() {
        memoRepository.getMemoList()
    }

    fun onAddClick() {
        onUpdateMoveView(MemoViewType.ADD)
    }

    fun onMemoClick() {
        onUpdateMoveView(MemoViewType.DETAIL)
    }
}