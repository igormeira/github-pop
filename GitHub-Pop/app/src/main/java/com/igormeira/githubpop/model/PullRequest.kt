package com.igormeira.githubpop.model

import com.google.gson.annotations.SerializedName
import com.igormeira.githubpop.util.StringUtil

data class PullRequest (
    @SerializedName("title")
    var title: String,

    @SerializedName("body")
    var description: String,

    @SerializedName("url")
    var link: String,

    @SerializedName("created_at")
    var date: String,

    @SerializedName("user")
    var user: User
) {
    fun getFormattedDate(): String {
        return StringUtil.formattedDateFromString("yyyy-MM-dd", "dd-MMM-yyyy", date)
    }
}