package com.kosigo.payments.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.kosigo.payments.R
import com.kosigo.payments.data.model.State
import com.kosigo.payments.ui.WelcomeActivity
import com.kosigo.payments.ui.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.flow.collect
import org.kodein.di.generic.instance

class MainFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.main_fragment

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by instance()
    private lateinit var adapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.app_name)

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_sign_out) {
                viewModel.signOut()
            }
            true
        }

        adapter = MainAdapter()
        catalog_list.layoutManager = GridLayoutManager(context, 1)
        catalog_list.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when (it) {
                    is State.Loading -> {
                        progress_bar.show()
                    }
                    is State.Success -> {
                        progress_bar.hide()
                        adapter.setNewData(it.data)
                    }
                    is State.Failed -> {
                        progress_bar.hide()
                        showToast("Failed! ${it.message}")
                    }
                    is State.Finish -> {
                        MaterialDialog(requireContext()).show {
                            title(res = R.string.question_signout)
                            cornerRadius(16f)
                            positiveButton {
                                startActivity(Intent(requireContext(), WelcomeActivity::class.java))
                                activity?.finish()
                            }
                            negativeButton { }
                        }
                    }
                    else -> {
                        progress_bar.hide()
                    }
                }
            }
        }
    }
}