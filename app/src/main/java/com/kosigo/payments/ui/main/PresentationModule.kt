package com.kosigo.payments.ui.main

import androidx.fragment.app.Fragment
import com.kosigo.payments.di.KotlinViewModelProvider
import com.kosigo.payments.ui.auth.AuthViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("PresentationModule") {

    bind<MainViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { MainViewModel(instance()) }
    }

    bind<AuthViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AuthViewModel(instance()) }
    }

}
