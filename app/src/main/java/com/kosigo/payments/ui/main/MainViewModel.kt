package com.kosigo.payments.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosigo.payments.PaymentApplication
import com.kosigo.payments.data.model.PaymentItem
import com.kosigo.payments.data.model.State
import com.kosigo.payments.domain.PaymentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PaymentsRepository
) : ViewModel() {
    fun signOut() {
        PaymentApplication.instance.sharedPreferences.edit()
            .putString(PaymentApplication.TOKEN,"").apply()
        _viewState.value = State.finish()
    }

    private val _viewState = MutableStateFlow<State<List<PaymentItem>>>(State.loading())
    val viewState: StateFlow<State<List<PaymentItem>>> = _viewState

    init {
        viewModelScope.launch {
            repository.getPayments()
                .map { items ->
                    items.filter { it.currency !=null }
                }
                .catch {
                    it.message?.let { message -> _viewState.value = State.failed(message) }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _viewState.value = State.success(it)
                }
        }
    }
}

