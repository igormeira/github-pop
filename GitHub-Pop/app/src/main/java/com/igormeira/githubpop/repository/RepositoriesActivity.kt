package com.igormeira.githubpop.repository

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.igormeira.githubpop.R
import com.igormeira.githubpop.util.Constants
import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivity : AppCompatActivity() {

    lateinit var viewModel: RepositoriesViewModel
    lateinit var adapter: RepositoriesRecyclerAdapter
    lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

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
        languageName.text = language
        backButton.setOnClickListener{ finish() }
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
            startActivity(it)
        })

        viewModel.displayLoading.observe(this, Observer {
            loadingAnim.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(repositories_layout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoadRepositoryError.observe(this, Observer {
            Snackbar.make(repositories_layout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}