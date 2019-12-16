package com.rkddlsgur983.test.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class BasicUtils {

    companion object {

        fun convertToDateFormat(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA)
            return format.format(date)
        }

        fun getTime() = convertToDateFormat(Calendar.getInstance().time.time)
        
        fun showToast(context: Context?, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}