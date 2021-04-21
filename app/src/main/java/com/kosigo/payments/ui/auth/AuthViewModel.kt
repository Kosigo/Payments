package com.kosigo.payments.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosigo.payments.PaymentApplication
import com.kosigo.payments.data.model.PaymentItem
import com.kosigo.payments.data.model.State
import com.kosigo.payments.domain.PaymentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val paymentRepository: PaymentsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<State<List<PaymentItem>>>(State.loading())
    val viewState: StateFlow<State<List<PaymentItem>>> = _viewState

    fun auth(login: String, password: String) {
        if (login.isEmpty() || password.isEmpty()){
            _viewState.value = State.failed("Error")
            _viewState.value = State.loading()
            return
        }
        viewModelScope.launch {
            paymentRepository.login(login, password)
                .catch {
                    it.message?.let { message ->
                        _viewState.value = State.failed(message)
                        _viewState.value = State.loading()
                    }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    if(it.success.toBoolean()){
                        PaymentApplication.instance.sharedPreferences.edit()
                            .putString(PaymentApplication.TOKEN,it.response?.token).apply()
                        _viewState.value = State.complete()
                    } else {
                        _viewState.value = State.failed(it.error?.error_msg ?: "")
                        _viewState.value = State.loading()
                    }
                }
        }
    }
}