package com.igormeira.githubpop.api

import com.igormeira.githubpop.model.PullRequest
import com.igormeira.githubpop.model.Repositories
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    // REPOSITORIES //
    @GET("search/repositories?sort=stars")
    fun getRepositories(@Query("page") page: Int,
                        @Query("q") language: String
    ): Call<Repositories>

    // PULL REQUESTS //
    @GET("repos/{creator}/{name}/pulls")
    fun getPullRequests(
        @Path("creator") repoCreator: String?,
        @Path("name") repoName: String?
    ): Call<List<PullRequest>>

    companion object {
        fun getBaseService(): GitHubService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GitHubService::class.java)
        }
    }
}