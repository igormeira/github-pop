package com.igormeira.githubpop.model

import com.google.gson.annotations.SerializedName

data class UserLogin (

    @SerializedName("login")
    var username: String,

    @SerializedName("id")
    var id: String?
)