package com.kosigo.payments.data.model

sealed class State<T> {
    class Loading<T> : State<T>()
    class Complete<T> : State<T>()
    class Finish<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val message: String) : State<T>()

    companion object {
        fun <T> loading() =
            Loading<T>()

        fun <T> complete() =
            Complete<T>()

        fun <T> finish() =
            Finish<T>()

        fun <T> success(data: T) =
            Success(data)

        fun <T> failed(message: String) =
            Failed<T>(message)
    }
}
