package com.igormeira.githubpop.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    @SerializedName("items")
    var repositories: List<Repository>
)