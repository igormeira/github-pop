package com.igormeira.githubpop.view.pullrequest

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.igormeira.githubpop.R
import com.igormeira.githubpop.databinding.ActivityPullRequestsBinding
import com.igormeira.githubpop.util.Constants
import com.igormeira.githubpop.util.DialogUtil
import kotlinx.android.synthetic.main.activity_pull_requests.*

class PullRequestsActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityPullRequestsBinding
    private lateinit var viewModel: PullRequestViewModel
    private lateinit var adapter: PullRequestRecyclerAdapter
    private lateinit var repoName: String
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_pull_requests)

        setUpViewModel()
        setUpAdapter()
        setUpObservers()
        setUpViewElements()
    }

    private fun setUpViewElements() {
        dataBinding.toolbar.setNavigationOnClickListener { finish() }
        dataBinding.toolbar.title = repoName
    }

    private fun setUpViewModel() {
        repoName = intent.getStringExtra(Constants.REPOSITORY_NAME.name)!!
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

        viewModel.displayLoading.observe(this, Observer {
            if (it) loadingDialog = DialogUtil.showLadingDialog(this)
            else loadingDialog.dismiss()
        })

        viewModel.displayNoBrowserMessage.observe(this, Observer {
            Snackbar.make(dataBinding.pullRequestsLayout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(dataBinding.pullRequestsLayout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoadPullRequestsError.observe(this, Observer {
            Snackbar.make(dataBinding.pullRequestsLayout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}