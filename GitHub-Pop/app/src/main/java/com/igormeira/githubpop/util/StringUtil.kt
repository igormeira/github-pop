package com.igormeira.githubpop.util

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object StringUtil {
    fun formattedDateFromString(
        inputFormat: String?,
        outputFormat: String?,
        inputDate: String?
    ): String {
        var parsed: Date? = null
        var outputDate = ""
        val dfInput =
            SimpleDateFormat(inputFormat, Locale.getDefault())
        val dfOutput =
            SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = dfInput.parse(inputDate)
            outputDate = dfOutput.format(parsed)
        } catch (e: ParseException) {
            Log.e(
                Constraints.TAG,
                "ParseException - dateFormat"
            )
        }
        return outputDate
    }
}