package com.kosigo.payments.data

import com.kosigo.payments.data.repository.PaymentsRepositoryImpl
import com.kosigo.payments.data.retrofit.RetrofitService
import com.kosigo.payments.domain.PaymentsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("DataModule") {

    bind<PaymentsRepository>() with singleton { PaymentsRepositoryImpl(instance()) }
    bind() from singleton { instance<Retrofit>().create(RetrofitService::class.java) }
}
