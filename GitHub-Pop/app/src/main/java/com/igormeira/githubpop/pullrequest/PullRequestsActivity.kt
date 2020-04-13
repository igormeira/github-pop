package com.igormeira.githubpop.pullrequest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.igormeira.githubpop.R
import com.igormeira.githubpop.util.Constants
import kotlinx.android.synthetic.main.activity_pull_requests.*

class PullRequestsActivity : AppCompatActivity() {

    lateinit var viewModel: PullRequestViewModel
    lateinit var adapter: PullRequestRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)
        //toolbar.setNavigationOnClickListener { finish() }

        setUpViewModel()
        setUpAdapter()
        setUpObservers()
    }

    private fun setUpViewModel() {
        val repoName = intent.getStringExtra(Constants.REPOSITORY_NAME.name)!!
        val repoCreator = intent.getStringExtra(Constants.REPOSITORY_CREATOR.name)!!
        viewModel = ViewModelProviders.of(this, PullRequestViewModelFactory(
            this.application, repoName, repoCreator)).get(PullRequestViewModel::class.java)
    }

    private fun setUpAdapter() {
        adapter = PullRequestRecyclerAdapter(this, viewModel::onPullRequestClick)
        recyclerViewPullRequest.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewPullRequest.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.list.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.intent.observe(this, Observer {
            startActivity(it)
        })

        /*viewModel.displayEmptyMessage.observe(this, Observer {
            empty_list_text.visibility = if (it) View.VISIBLE else View.GONE
        })*/

        viewModel.displayNoBrowserMessage.observe(this, Observer {
            Snackbar.make(pull_requests_layout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(pull_requests_layout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoadPullRequestsError.observe(this, Observer {
            Snackbar.make(pull_requests_layout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}