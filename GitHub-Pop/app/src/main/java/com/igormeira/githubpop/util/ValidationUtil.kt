package com.igormeira.githubpop.util

import android.net.Uri

class ValidationUtil {

    companion object {
        fun isValidUri(url: String): Uri? {
            return if (!url.startsWith("http://") && !url.startsWith("https://")) {
                Uri.parse("http://$url")
            } else {
                Uri.parse(url)
            }
        }
    }

}