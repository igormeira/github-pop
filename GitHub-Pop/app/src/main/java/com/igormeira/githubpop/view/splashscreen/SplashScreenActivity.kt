package com.igormeira.githubpop.view.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.igormeira.githubpop.R

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewModel : SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.intent.observe(this, Observer{
            startActivity(it)
            finish()
        })
    }
}