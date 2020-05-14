package com.igormeira.githubpop.view.pullrequest

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PullRequestViewModelFactory(private val application: Application, private val repoName: String,
                                  private val repoCreator: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestViewModel(application, repoName, repoCreator) as T
    }
}