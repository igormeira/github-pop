package com.igormeira.githubpop.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login")
    var username: String,

    @SerializedName("avatar_url")
    var avatar: String?
)