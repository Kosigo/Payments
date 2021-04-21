package com.kosigo.payments.data.retrofit

import com.kosigo.payments.PaymentApplication
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {

        val newRequest = it.newBuilder()
        val token = PaymentApplication.instance.sharedPreferences
            .getString(PaymentApplication.TOKEN, "") ?: ""
        if (token.isNotEmpty()) {
            val url = it.url.newBuilder()
                .addQueryParameter("token", token)
                .build()
            newRequest.url(url)
        }

        chain.proceed(newRequest.build())
    }
}
