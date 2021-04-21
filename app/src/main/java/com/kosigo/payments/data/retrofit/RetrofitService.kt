package com.kosigo.payments.data.retrofit

import com.kosigo.payments.data.model.LoginResponse
import com.kosigo.payments.data.model.PaymentResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface RetrofitService {

    @GET("payments")
    suspend fun getPayments(): PaymentResponse

    @Multipart
    @POST("login")
    suspend fun login(
        @Part("login") login: RequestBody,
        @Part("password") pass: RequestBody
    ): LoginResponse
}
