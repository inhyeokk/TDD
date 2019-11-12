package com.rkddlsgur983.test.util

import java.text.SimpleDateFormat
import java.util.*

class BasicUtil {

    companion object {

        fun convertToDateFormat(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA)
            return format.format(date)
        }
    }
}