package com.kosigo.payments.data.repository

import com.kosigo.payments.data.retrofit.RetrofitService
import com.kosigo.payments.domain.PaymentsRepository
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


class PaymentsRepositoryImpl(
    private val retrofitService: RetrofitService
) : PaymentsRepository {

    override fun login(login: String, password: String) = flow {
        val response = retrofitService.login(
            login.toRequestBody(login.toMediaTypeOrNull()),
            password.toRequestBody(password.toMediaTypeOrNull())
        )
        emit(response)
    }

    override fun getPayments() = flow {
        val response = retrofitService.getPayments()
        emit(response.response)
    }
}