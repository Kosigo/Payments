package com.kosigo.payments.data.retrofit

import com.kosigo.payments.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class UserAgentInterceptor : Interceptor {
    private val userAgent = "payments/${BuildConfig.VERSION_NAME} ${System.getProperty("http.agent")}"

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .request()
        .newBuilder()
        .header("User-Agent", userAgent)
        .header("app-key", "12345")
        .header("v", "1")
        .build()
        .let { chain.proceed(it) }
}
