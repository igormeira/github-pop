package com.igormeira.githubpop.view.repository

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.igormeira.githubpop.R
import com.igormeira.githubpop.github.api.ConnectivityService
import com.igormeira.githubpop.github.data.RepositoryFactory
import com.igormeira.githubpop.model.Repository
import com.igormeira.githubpop.view.pullrequest.PullRequestsActivity
import com.igormeira.githubpop.util.Constants
import org.koin.core.KoinComponent
import org.koin.core.inject

class RepositoriesViewModel(application: Application,
                            private val language: String) : AndroidViewModel(application),
    KoinComponent {

    private val connectivityService: ConnectivityService by inject()

    private val context = application.applicationContext
    lateinit var list: LiveData<PagedList<Repository>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayConnectivityMessage = MutableLiveData<String>()
    var displayLoadRepositoryError = MutableLiveData<String>()
    var displayLoading = MutableLiveData<Boolean>()

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        displayLoading.postValue(true)
        val dataSourceFactory = RepositoryFactory(::onInitialFetchCompleted, ::onLoadRepositoriesError, language)
        val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20).setEnablePlaceholders(false).build()
        list = LivePagedListBuilder<Int, Repository>(dataSourceFactory, config).build()
    }

    private fun onInitialFetchCompleted() {
        displayLoading.postValue(false)
    }

    private fun onLoadRepositoriesError() {
        displayLoading.postValue(false)
        if (connectivityService.isNetworkAvailable(context))
            displayLoadRepositoryError.postValue(context.getString(R.string.load_repo_error))
        else displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
    }

    fun onRepositoryClick(repository: Repository) {
        intent.postValue(Intent(context, PullRequestsActivity::class.java).apply {
            putExtra(Constants.REPOSITORY_CREATOR.name, repository.user.username)
            putExtra(Constants.REPOSITORY_NAME.name, repository.title)
        })
    }
}