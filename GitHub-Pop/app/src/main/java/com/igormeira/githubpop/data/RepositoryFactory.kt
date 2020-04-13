package com.igormeira.githubpop.data

import androidx.paging.DataSource
import com.igormeira.githubpop.model.Repository

class RepositoryFactory (private val initialFetchCompletedCallback: (Boolean) -> Unit,
                         private val errorCallback: () -> Unit) : DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> {
        return RepositoryData(initialFetchCompletedCallback, errorCallback)
    }
}