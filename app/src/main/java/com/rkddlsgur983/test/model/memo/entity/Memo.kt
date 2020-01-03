package com.rkddlsgur983.test.model.memo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rkddlsgur983.test.view.memo.entity.MemoCategory

@Entity(tableName = "memo")
class Memo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val category: MemoCategory,
    val contents: String,
    val date: String
)