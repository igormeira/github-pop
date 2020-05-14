package com.igormeira.githubpop.github.data

import androidx.paging.DataSource
import com.igormeira.githubpop.model.PullRequest

class PullRequestFactory (private val initialFetchCompletedCallback: () -> Unit,
                          private val errorCallback: () -> Unit, private val repoCreator: String,
                          private val repoName: String) : DataSource.Factory<Int, PullRequest>() {

    override fun create(): DataSource<Int, PullRequest> {
        return PullRequestData(initialFetchCompletedCallback, errorCallback, repoCreator, repoName)
    }
}