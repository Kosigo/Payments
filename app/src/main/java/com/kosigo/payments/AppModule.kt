package com.kosigo.payments

import com.kosigo.payments.data.retrofit.AuthenticationInterceptor
import com.kosigo.payments.data.retrofit.UserAgentInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = Kodein.Module("AppModule") {

    bind() from singleton { AuthenticationInterceptor() }
    bind() from singleton { UserAgentInterceptor() }

    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addInterceptor(instance<AuthenticationInterceptor>())
            .addInterceptor(instance<UserAgentInterceptor>())
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BuildConfig.GRADLE_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(instance())
            .build()
    }
}
