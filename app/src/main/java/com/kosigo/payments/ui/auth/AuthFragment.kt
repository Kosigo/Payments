package com.kosigo.payments.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kosigo.payments.BuildConfig
import com.kosigo.payments.R
import com.kosigo.payments.data.model.State
import com.kosigo.payments.ui.MainActivity
import com.kosigo.payments.ui.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.coroutines.flow.collect
import org.kodein.di.generic.instance


class AuthFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.auth_fragment

    companion object {
        fun newInstance() = AuthFragment()
    }

    private val viewModel: AuthViewModel by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_login!!.onFocusChangeListener =
            View.OnFocusChangeListener { _, _ -> disableError() }
        et_password!!.onFocusChangeListener =
            View.OnFocusChangeListener { _, _ -> disableError() }

        button_sign_in.setOnClickListener {
            disableError()
            hideKeyboard(activity)
            viewModel.auth(et_login.text.toString(), et_password.text.toString())
        }
        tv_version.text = String.format(getString(R.string.text_version), BuildConfig.VERSION_NAME)


        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when (it) {
                    is State.Loading -> {
                    }
                    is State.Complete -> {
                        authSuccess()
                    }
                    is State.Failed -> {
                        showErrorSignIn()
                        button_sign_in.visibility = View.VISIBLE
                        showToast("Failed! ${it.message}")
                    }
                    else -> {
                        button_sign_in.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun disableError() {
        til_login.isErrorEnabled = false
        til_password.isErrorEnabled = false
    }

    private fun authSuccess() {
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    private fun showErrorSignIn() {
        til_login.error = getString(R.string.text_error_login_or_password)
        til_login.isErrorEnabled = true
        til_password.error = getString(R.string.text_error_login_or_password)
        til_password.isErrorEnabled = true
    }

}