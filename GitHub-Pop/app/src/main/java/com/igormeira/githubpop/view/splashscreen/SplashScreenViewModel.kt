package com.igormeira.githubpop.view.splashscreen

import android.app.Application
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.igormeira.githubpop.view.login.LoginActivity
import com.igormeira.githubpop.view.selection.SelectionActivity
import com.igormeira.githubpop.util.PreferencesUtil

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    private var context = application.applicationContext
    var intent = MutableLiveData<Intent>()

    init {
        Handler().postDelayed({startApplication()}, 2000)
    }

    private fun startApplication() {
        if (PreferencesUtil.getId(context)!!.isNotEmpty())
            intent.postValue(Intent(context, SelectionActivity::class.java))
        else intent.postValue(Intent(context, LoginActivity::class.java))
    }

}