package com.igormeira.githubpop.repository

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepositoriesViewModelFactory(private val application: Application,
                                   private val language: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoriesViewModel(application, language) as T
    }
}