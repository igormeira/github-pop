package com.igormeira.githubpop.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.igormeira.githubpop.R

class DialogUtil {

    companion object {
        fun showLadingDialog(context: Context): AlertDialog {
            val builder = MaterialAlertDialogBuilder(context)
            builder
            .setView(R.layout.dialog_loading)
            .setCancelable(false)
            val dialog = builder.show()
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }
}