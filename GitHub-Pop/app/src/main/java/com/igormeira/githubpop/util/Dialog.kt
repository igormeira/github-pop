package com.igormeira.githubpop.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.igormeira.githubpop.R

object Dialog {
    fun showLoadingDialog(ctx: Context?): ProgressDialog {
        val fpDialog = ProgressDialog.show(
            ctx, "",
            "", true
        )
        fpDialog.setContentView(R.layout.item_loading)
        fpDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return fpDialog
    }
}