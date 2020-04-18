package com.igormeira.githubpop.selection

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.igormeira.githubpop.repository.RepositoriesActivity
import com.igormeira.githubpop.util.Constants

class SelectionViewModel(application: Application) : AndroidViewModel(application) {

    private var context = application.applicationContext

    var intent = MutableLiveData<Intent>()


    fun onLanguageSelected(language : String) {
        println(language)
        intent.postValue(Intent(context, RepositoriesActivity::class.java).apply {
            putExtra(Constants.LANGUAGE.name, language)
        })
    }
}