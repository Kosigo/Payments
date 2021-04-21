package com.kosigo.payments

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.kosigo.payments.data.dataModule
import com.kosigo.payments.domain.domainModule
import com.kosigo.payments.ui.main.presentationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule


class PaymentApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@PaymentApplication))
        import(appModule)
        import(dataModule)
        import(domainModule)
        import(presentationModule)
    }

    lateinit var sharedPreferences: SharedPreferences
    private val nightMode = "night_mode"


    override fun onCreate() {
        super.onCreate()
        instance = this

        sharedPreferences =  getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE).apply {
            getInt(nightMode, AppCompatDelegate.MODE_NIGHT_YES).apply {
                AppCompatDelegate.setDefaultNightMode(this)
            }
        }
    }

    companion object {
        lateinit var instance: PaymentApplication
        const val TOKEN = "TOKEN"
    }
}
