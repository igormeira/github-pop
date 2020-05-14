package com.igormeira.githubpop.github.data

import androidx.paging.DataSource
import com.igormeira.githubpop.model.Repository

class RepositoryFactory (private val initialFetchCompletedCallback: () -> Unit,
                         private val errorCallback: () -> Unit,
                         private val language: String) : DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> {
        return RepositoryData(initialFetchCompletedCallback, errorCallback, language)
    }
}