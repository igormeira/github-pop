package com.igormeira.githubpop

import android.app.Application
import com.igormeira.githubpop.apirequests.ConnectivityService
import com.igormeira.githubpop.apirequests.GitHubService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { ConnectivityService() }
    single { GitHubService.getBaseService() }
}

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}