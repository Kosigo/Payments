package com.kosigo.payments.ui.main

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kosigo.payments.R
import com.kosigo.payments.data.model.PaymentItem
import com.kosigo.payments.toDecimalString
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter : BaseMultiItemQuickAdapter<PaymentItem, BaseViewHolder>(arrayListOf()) {
    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("HH:mm")

    init {
        addItemType(0, R.layout.adapter_list_item)
    }

    override fun convert(helper: BaseViewHolder, item: PaymentItem) {
        helper.setText(R.id.tv_amount, item.amount.toDecimalString())
        helper.setText(R.id.tv_title, item.currency)
        helper.setText(R.id.tv_sub_title, item.desc)
        helper.setText(R.id.tv_date, format.format(Date(item.created)))
    }
}
