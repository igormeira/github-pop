package com.igormeira.githubpop.view.selection

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.igormeira.githubpop.R
import com.igormeira.githubpop.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivitySelectionBinding
    private lateinit var viewModel : SelectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_selection)

        setUpViewModel()
        setUpObservers()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    private fun setUpObservers() {
        viewModel.intent.observe(this, Observer {
            startActivity(it,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        })

        viewModel.intentLogout.observe(this, Observer {
            finishAffinity()
            startActivity(it)
        })
    }
}
