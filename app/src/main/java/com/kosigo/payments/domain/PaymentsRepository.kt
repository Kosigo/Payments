package com.kosigo.payments.domain

import com.kosigo.payments.data.model.LoginResponse
import com.kosigo.payments.data.model.PaymentItem
import kotlinx.coroutines.flow.Flow

interface PaymentsRepository {
    fun login(login: String, password: String): Flow<LoginResponse>
    fun getPayments(): Flow<List<PaymentItem>>
}