package com.igormeira.githubpop.github.data

import com.igormeira.githubpop.github.api.GitHubService
import com.igormeira.githubpop.model.UserLogin
import okhttp3.Credentials
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRequest(private val loginCompletedCallback: (Boolean, String?) -> Unit,
                   private val errorCallback: () -> Unit,
                   private val username: String,
                   private val password: String) : KoinComponent {

    private val gitHubService: GitHubService by inject()

    fun login() {
        gitHubService.getUserInfo(Credentials.basic(username,password))
            .enqueue(object : Callback<UserLogin> {
            override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                loginCompletedCallback.invoke(response.isSuccessful, response.body()?.id)
            }

            override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                errorCallback.invoke()
            }
        })
    }

}