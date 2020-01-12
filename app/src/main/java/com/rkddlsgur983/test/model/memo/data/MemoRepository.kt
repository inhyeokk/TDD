package com.rkddlsgur983.test.model.memo.data

import com.rkddlsgur983.test.model.memo.MemoDatabase
import com.rkddlsgur983.test.model.memo.entity.Memo

class MemoRepository(database: MemoDatabase) {

    private val memoDao = database.memoDao()

    fun insert(memo: Memo) = memoDao.insert(memo)

    fun getMemo(id: Int) = memoDao.getMemo(id)

    fun getMemoList() = memoDao.getMemoList()
}