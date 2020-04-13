package com.igormeira.githubpop.repository

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.igormeira.githubpop.R
import kotlinx.android.synthetic.main.activity_repositories.*
import java.util.*

class RepositoriesActivity : AppCompatActivity() {

    lateinit var viewModel: RepositoriesViewModel
    lateinit var adapter: RepositoriesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)
        setUpAdapter()
        setUpObservers()
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

        /*viewModel.displayEmptyMessage.observe(this, Observer {
            empty_list_text.visibility = if (it) View.VISIBLE else View.GONE
        })*/

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(repositories_layout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoadRepositoryError.observe(this, Observer {
            Snackbar.make(repositories_layout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}