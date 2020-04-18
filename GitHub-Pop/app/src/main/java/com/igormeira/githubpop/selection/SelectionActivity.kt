package com.igormeira.githubpop.selection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.igormeira.githubpop.R
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity() {

    lateinit var viewModel : SelectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        setUpViewModel()
        setUpViewElements()
        setUpObservers()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
    }

    private fun setUpViewElements() {
        cppCardImage.setOnClickListener { viewModel.onLanguageSelected("Cpp") }
        javaCardImage.setOnClickListener { viewModel.onLanguageSelected("Java") }
        jsCardImage.setOnClickListener{ viewModel.onLanguageSelected("JavaScript") }
        kotlinCardImage.setOnClickListener { viewModel.onLanguageSelected("Kotlin") }
        pythonCardImage.setOnClickListener { viewModel.onLanguageSelected("Python") }
        swiftCardImage.setOnClickListener { viewModel.onLanguageSelected("Swift") }
    }

    private fun setUpObservers() {
        viewModel.intent.observe(this, Observer {
            startActivity(it)
        })
    }
}
