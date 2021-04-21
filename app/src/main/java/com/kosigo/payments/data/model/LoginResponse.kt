package com.kosigo.payments.data.model

data class Token(
    val token: String
)

data class Error(
    val error_code: Int,
    val error_msg: String
)

data class LoginResponse(
    val success: String,
    val response: Token?,
    val error: Error?
)

