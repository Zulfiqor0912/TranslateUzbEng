package uz.gita.translateuzbeng.utils


import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import uz.gita.translateuzbeng.R


fun String.spannable(query: String): SpannableString {
    val span = SpannableString(this)
    val color = ForegroundColorSpan(ContextCompat.getColor(uz.gita.translateuzbeng.app.App.instance, R.color.black))
    var startIndex = this.indexOf(query, 0, true)
    if (startIndex > -1) {
        span.setSpan(
            color,
            startIndex,
            startIndex + query.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return span
}