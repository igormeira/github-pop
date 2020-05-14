package com.igormeira.githubpop.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


class KeyboardUtil {

    companion object {
        fun hideKeyboardOnFocus(view: View, activity: Activity) {
            if (view is EditText) {
                view.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        val imm = activity
                            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(
                            activity.window.decorView.rootView.windowToken,
                            0
                        )
                    }
                }
            }
        }
    }
}