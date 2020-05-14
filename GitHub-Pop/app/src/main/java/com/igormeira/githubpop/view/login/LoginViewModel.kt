package com.igormeira.githubpop.view.login

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.igormeira.githubpop.R
import com.igormeira.githubpop.github.data.LoginRequest
import com.igormeira.githubpop.view.selection.SelectionActivity
import com.igormeira.githubpop.util.PreferencesUtil

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private lateinit var username: String
    var intent = MutableLiveData<Intent>()
    var loading = MutableLiveData<Boolean>()
    var invalidUserWarning = MutableLiveData<Boolean>()
    var displayLoginError = MutableLiveData<String>()

    fun onClickLogin(username : String, password : String) {
        loading.postValue(true)
        if (username.isNotEmpty() && password.isNotEmpty()) {
            this.username = username
            LoginRequest(::onInitialFetchCompleted,
                ::onLoadRepositoriesError, username, password).login()
        }
        else {
            invalidUserWarning.postValue(true)
        }

    }

    private fun onInitialFetchCompleted(success: Boolean, id: String?) {
        loading.postValue(false)
        id?.let { PreferencesUtil.saveId(context, id)
            PreferencesUtil.saveUsername(context, username) }
        if (success) intent.postValue(Intent(context, SelectionActivity::class.java))
        else invalidUserWarning.postValue(true)
    }

    private fun onLoadRepositoriesError() {
        displayLoginError.postValue(context.getString(R.string.load_repo_error))
    }
}