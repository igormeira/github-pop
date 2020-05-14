package com.igormeira.githubpop.view.pullrequest

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.igormeira.githubpop.R
import com.igormeira.githubpop.github.api.ConnectivityService
import com.igormeira.githubpop.github.data.PullRequestFactory
import com.igormeira.githubpop.model.PullRequest
import com.igormeira.githubpop.util.ValidationUtil
import org.koin.core.KoinComponent
import org.koin.core.inject

class PullRequestViewModel(application: Application, private val repoName: String,
                           private val repoCreator: String) : AndroidViewModel(application),
    KoinComponent {

    private val connectivityService: ConnectivityService by inject()

    private val context = application.applicationContext
    lateinit var list: LiveData<PagedList<PullRequest>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayNoBrowserMessage = MutableLiveData<String>()
    var displayConnectivityMessage = MutableLiveData<String>()
    var displayLoadPullRequestsError = MutableLiveData<String>()
    var displayLoading = MutableLiveData<Boolean>()

    init {
        loadPullRequests()
    }

    private fun loadPullRequests() {
        displayLoading.postValue(true)
        val dataSourceFactory = PullRequestFactory(::onInitialFetchCompleted, ::onLoadPullRequestsError, repoCreator, repoName)
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false).build()
        list = LivePagedListBuilder<Int, PullRequest>(dataSourceFactory, config).build()
    }

    private fun onInitialFetchCompleted() {
        displayLoading.postValue(false)
    }

    private fun onLoadPullRequestsError() {
        displayLoading.postValue(false)
        if (connectivityService.isNetworkAvailable(context))
            displayLoadPullRequestsError.postValue(context.getString(R.string.load_pr_error))
        else displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
    }

    fun onPullRequestClick(pullRequest: PullRequest) {
        try {
            intent.postValue(Intent(Intent.ACTION_VIEW, ValidationUtil.isValidUri(pullRequest.link)))
        } catch (e: ActivityNotFoundException) {
            displayNoBrowserMessage.postValue(context.getString(R.string.browser_error))
        }
    }
}