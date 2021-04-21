package com.kosigo.payments.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kosigo.payments.PaymentApplication
import com.kosigo.payments.PaymentApplication.Companion.TOKEN
import com.kosigo.payments.R
import com.kosigo.payments.ui.auth.AuthFragment


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val token = PaymentApplication.instance.sharedPreferences.getString(TOKEN,"") ?:""
        if(token.isNotEmpty()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.welcome_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthFragment.newInstance())
                .commitNow()
        }
    }
}