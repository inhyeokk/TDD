package com.rkddlsgur983.test.model.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rkddlsgur983.test.model.memo.dao.MemoConverter
import com.rkddlsgur983.test.model.memo.dao.MemoDao
import com.rkddlsgur983.test.model.memo.entity.Memo
import java.util.concurrent.Executors


@Database(entities = [Memo::class], version = 1, exportSchema = false)
@TypeConverters(MemoConverter::class)
abstract class MemoDatabase: RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object {
        @Volatile
        lateinit var INSTANCE: MemoDatabase
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): MemoDatabase {
            synchronized(MemoDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDatabase::class.java,
                    "memo-db"
                ).build()
            }
            return INSTANCE
        }
    }
}