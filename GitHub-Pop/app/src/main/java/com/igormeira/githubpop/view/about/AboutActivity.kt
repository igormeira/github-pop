package com.igormeira.githubpop.view.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.igormeira.githubpop.R
import com.igormeira.githubpop.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_about)

        setUpViewElements()
    }

    private fun setUpViewElements() {
        dataBinding.aboutToolbar.setNavigationOnClickListener { finish() }
    }
}
