package com.igormeira.githubpop.data

import androidx.paging.DataSource
import com.igormeira.githubpop.model.PullRequest

class PullRequestFactory (private val initialFetchCompletedCallback: (Boolean) -> Unit,
                          private val errorCallback: () -> Unit, private val repoCreator: String,
                          private val repoName: String) : DataSource.Factory<Int, PullRequest>() {

    override fun create(): DataSource<Int, PullRequest> {
        return PullRequestData(initialFetchCompletedCallback, errorCallback, repoCreator, repoName)
    }
}