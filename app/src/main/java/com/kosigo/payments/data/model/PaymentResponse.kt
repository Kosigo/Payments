package com.kosigo.payments.data.model

import com.chad.library.adapter.base.entity.MultiItemEntity

data class PaymentResponse(
    val success: String,
    val response: List<PaymentItem>
)

data class PaymentItem(
    val desc: String,
    val amount: Double,
    val currency: String?,
    val created: Long
): MultiItemEntity {
    override fun getItemType(): Int {
        return 0
    }
}
