package com.igormeira.githubpop.model

import com.google.gson.annotations.SerializedName

data class Repository (
    @SerializedName("name")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("stargazers_count")
    var stars: Int,

    @SerializedName("forks_count")
    var forks: Int,

    @SerializedName("owner")
    var user: User
) {

    fun getStars(): String {
        return stars.toString()
    }

    fun getForks(): String {
        return forks.toString()
    }
}