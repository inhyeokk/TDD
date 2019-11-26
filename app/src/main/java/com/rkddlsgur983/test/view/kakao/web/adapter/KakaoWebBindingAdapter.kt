package com.rkddlsgur983.test.view.kakao.web.adapter

import android.util.Log
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.rkddlsgur983.test.util.BasicUtil
import java.net.URLDecoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class KakaoWebBindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("text_bold")
        fun setTextBold(textView: TextView, text: String) {
            textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        @JvmStatic
        @BindingAdapter("url_decode")
        fun setUrlDecode(textView: TextView, url: String) {
            textView.text = URLDecoder.decode(url, "UTF-8")
        }

        @JvmStatic
        @BindingAdapter("convert_date_format")
        fun convertDateFormat(textView: TextView, date: String) {
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA)
            try {
                val d = df.parse(date)!!
                val time = d.time
                textView.text = BasicUtil.convertToDateFormat(time)
            } catch (e: ParseException) {
                Log.e("ParseError", e.message!!)
            }
        }
    }
}