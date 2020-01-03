package com.rkddlsgur983.test.model.memo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rkddlsgur983.test.model.memo.entity.Memo

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(memo: Memo)

    @Query(value = "SELECT * FROM memo WHERE id = :id")
    fun getMemo(id: Int): LiveData<Memo>

    @Query(value = "SELECT * FROM memo")
    fun getMemoList(): LiveData<List<Memo>>
}