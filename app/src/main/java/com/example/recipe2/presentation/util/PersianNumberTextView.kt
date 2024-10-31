package com.example.recipe2.presentation.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class PersianNumberTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setText(text: CharSequence?, type: BufferType?) {
        val convertedText = convertEnglishNumbersToPersian(text.toString())
        super.setText(convertedText, type)
    }

    private fun convertEnglishNumbersToPersian(text: String): String {
        val englishNumbers = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        val persianNumbers = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")

        var result = text
        for (i in englishNumbers.indices) {
            result = result.replace(englishNumbers[i], persianNumbers[i])
        }
        return result
    }
}