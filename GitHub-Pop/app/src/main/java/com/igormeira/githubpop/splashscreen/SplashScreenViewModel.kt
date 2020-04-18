package com.igormeira.githubpop.splashscreen

import android.app.Application
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.igormeira.githubpop.selection.SelectionActivity

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    private var context = application.applicationContext
    var intent = MutableLiveData<Intent>()

    init {
        Handler().postDelayed({startApplication()}, 2000)
    }

    private fun startApplication() {
        intent.postValue(Intent(context, SelectionActivity::class.java))
    }

}