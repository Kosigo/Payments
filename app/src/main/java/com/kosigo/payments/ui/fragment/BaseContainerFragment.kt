package com.kosigo.payments.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes

abstract class BaseContainerFragment : InjectionFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutResourceId, null).also {
            Log.d("BaseContainerFragment","onCreateView ${javaClass.simpleName}")
        }

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard(act: Activity?) {
        if (act != null && act.currentFocus != null) {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(act.currentFocus!!.windowToken, 0)
        }
    }
}
