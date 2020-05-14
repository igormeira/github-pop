package com.igormeira.githubpop.view.selection

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.igormeira.githubpop.view.about.AboutActivity
import com.igormeira.githubpop.view.login.LoginActivity
import com.igormeira.githubpop.view.repository.RepositoriesActivity
import com.igormeira.githubpop.util.Constants
import com.igormeira.githubpop.util.PreferencesUtil

class SelectionViewModel(application: Application) : AndroidViewModel(application) {

    private var context = application.applicationContext
    var username : String
    var intent = MutableLiveData<Intent>()
    var intentLogout = MutableLiveData<Intent>()

    init {
        username = PreferencesUtil.getUsername(context).toString()
    }

    fun onClickInfo() {
        intent.postValue(Intent(context, AboutActivity::class.java))
    }

    fun onClickLogout() {
        PreferencesUtil.logout(context)
        intentLogout.postValue(Intent(context, LoginActivity::class.java))
    }

    fun onLanguageSelected(language : String) {
        println(language)
        intent.postValue(Intent(context, RepositoriesActivity::class.java).apply {
            putExtra(Constants.LANGUAGE.name, language)
        })
    }
}