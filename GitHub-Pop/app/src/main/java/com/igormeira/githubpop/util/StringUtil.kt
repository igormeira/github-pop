package com.igormeira.githubpop.util

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object StringUtil {
    fun formateDateFromstring(
        inputFormat: String?,
        outputFormat: String?,
        inputDate: String?
    ): String {
        var parsed: Date? = null
        var outputDate = ""
        val df_input =
            SimpleDateFormat(inputFormat, Locale.getDefault())
        val df_output =
            SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = df_input.parse(inputDate)
            outputDate = df_output.format(parsed)
        } catch (e: ParseException) {
            Log.e(
                Constraints.TAG,
                "ParseException - dateFormat"
            )
        }
        return outputDate
    }
}