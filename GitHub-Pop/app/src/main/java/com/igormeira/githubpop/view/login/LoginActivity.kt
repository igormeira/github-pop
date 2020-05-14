package com.igormeira.githubpop.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.igormeira.githubpop.R
import com.igormeira.githubpop.databinding.ActivityLoginBinding
import com.igormeira.githubpop.util.DialogUtil
import com.igormeira.githubpop.util.KeyboardUtil

class LoginActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityLoginBinding
    private lateinit var viewModel : LoginViewModel
    private lateinit var loading : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setUpViewModel()
        setUpViewElements()
        setUpObservers()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private fun setUpViewElements() {
        dataBinding.loginMaterialButton.setOnClickListener {
            viewModel.onClickLogin(
                dataBinding.loginUsernameInput.text.toString(),
                dataBinding.loginPasswordInput.text.toString())
        }

        KeyboardUtil.hideKeyboardOnFocus(dataBinding.loginUsernameInput, this)
        KeyboardUtil.hideKeyboardOnFocus(dataBinding.loginPasswordInput, this)
    }

    private fun setUpObservers() {
        viewModel.intent.observe(this, Observer {
            startActivity(it)
            finish()
        })

        viewModel.loading.observe(this, Observer {
            if (it) loading = DialogUtil.showLadingDialog(this)
            else loading.dismiss()
        })

        viewModel.invalidUserWarning.observe(this, Observer {
            if (it) dataBinding.loginWarning.visibility = View.VISIBLE
            else dataBinding.loginWarning.visibility = View.INVISIBLE
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(dataBinding.loginLayout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoginError.observe(this, Observer {
            Snackbar.make(dataBinding.loginLayout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}
