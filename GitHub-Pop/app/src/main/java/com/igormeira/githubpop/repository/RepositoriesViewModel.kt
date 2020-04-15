package com.igormeira.githubpop.repository

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.igormeira.githubpop.R
import com.igormeira.githubpop.api.ConnectivityService
import com.igormeira.githubpop.data.RepositoryFactory
import com.igormeira.githubpop.model.Repository
import com.igormeira.githubpop.pullrequest.PullRequestsActivity
import com.igormeira.githubpop.util.Constants
import org.koin.core.KoinComponent
import org.koin.core.inject

class RepositoriesViewModel(application: Application) : AndroidViewModel(application),
    KoinComponent {

    private val connectivityService: ConnectivityService by inject()

    private val context = application.applicationContext

    lateinit var list: LiveData<PagedList<Repository>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayConnectivityMessage = MutableLiveData<String>()
    var displayLoadRepositoryError = MutableLiveData<String>()

    init {
        loadRepositories()
        if (!connectivityService.isNetworkAvailable(context)) {
            displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
        }
    }

    private fun loadRepositories() {
        val dataSourceFactory = RepositoryFactory(::onInitialFetchCompleted, ::onLoadRepositoriesError)
        val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20).setEnablePlaceholders(false).build()
        list = LivePagedListBuilder<Int, Repository>(dataSourceFactory, config).build()
    }

    private fun onInitialFetchCompleted(isEmpty: Boolean) {
        displayEmptyMessage.postValue(isEmpty)
    }

    private fun onLoadRepositoriesError() {
        displayLoadRepositoryError.postValue(context.getString(R.string.load_repo_error))
    }

    fun onRepositoryClick(repository: Repository) {
        intent.postValue(Intent(context, PullRequestsActivity::class.java).apply {
            putExtra(Constants.REPOSITORY_CREATOR.name, repository.user?.username)
            putExtra(Constants.REPOSITORY_NAME.name, repository.title)
        })
    }
}