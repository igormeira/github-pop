package com.igormeira.githubpop.view.repository

import android.app.ActivityOptions
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
import com.igormeira.githubpop.databinding.ActivityRepositoriesBinding
import com.igormeira.githubpop.util.Constants
import com.igormeira.githubpop.util.DialogUtil
import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityRepositoriesBinding
    private lateinit var viewModel: RepositoriesViewModel
    private lateinit var adapter: RepositoriesRecyclerAdapter
    private lateinit var language: String
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repositories)

        setUpViewModel()
        setUpViewElements()
        setUpAdapter()
        setUpObservers()
    }

    private fun setUpViewModel() {
        language = intent.getStringExtra(Constants.LANGUAGE.name)!!
        viewModel = ViewModelProviders.of(this,
            RepositoriesViewModelFactory(this.application, language))
            .get(RepositoriesViewModel::class.java)
    }

    private fun setUpViewElements() {
        dataBinding.toolbar.title = language
        dataBinding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setUpAdapter() {
        adapter = RepositoriesRecyclerAdapter(this, viewModel::onRepositoryClick)
        recyclerViewRepository.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewRepository.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.list.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.intent.observe(this, Observer {
            startActivity(it,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        })

        viewModel.displayLoading.observe(this, Observer {
            if (it) loadingDialog = DialogUtil.showLadingDialog(this)
            else loadingDialog.dismiss()
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(dataBinding.repositoriesLayout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoadRepositoryError.observe(this, Observer {
            Snackbar.make(dataBinding.repositoriesLayout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}