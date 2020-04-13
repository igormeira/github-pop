package com.igormeira.githubpop.data

import androidx.paging.PageKeyedDataSource
import com.igormeira.githubpop.apirequests.GitHubService
import com.igormeira.githubpop.model.Repositories
import com.igormeira.githubpop.model.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryData (private val initialFetchCompletedCallback: (Boolean) -> Unit,
                      private val errorCallback: () -> Unit) : PageKeyedDataSource<Int, Repository>(),
    KoinComponent {

    private val gitHubService: GitHubService by inject()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
        gitHubService.getRepositories(1).enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                val list = response.body()?.repositories ?: listOf()
                callback.onResult(list, null, 2)
                initialFetchCompletedCallback.invoke(list.isEmpty())
            }

            override fun onFailure(call: Call<Repositories>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        gitHubService.getRepositories(params.key).enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                val list = response.body()?.repositories ?: listOf()
                callback.onResult(list, params.key + 1)
            }

            override fun onFailure(call: Call<Repositories>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {}
}