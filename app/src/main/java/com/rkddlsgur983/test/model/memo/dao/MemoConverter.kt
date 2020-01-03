package com.rkddlsgur983.test.model.memo.dao

import androidx.room.TypeConverter
import com.rkddlsgur983.test.view.memo.entity.MemoCategory

class MemoConverter {

    @TypeConverter
    fun fromDatabase(value: String) = MemoCategory.fromString(value)

    @TypeConverter
    fun toDatabase(category: MemoCategory) = category.value
}