package kz.rza383.domain.entity

sealed class Result<T> {
    class Success<T>(val data: T?): Result<T>()
    sealed class Error<T>(): Result<T>() {

        class NetworkError<T>(): Error<T>()
        class ApiError<T>(val code: Int, val message: String): Error<T>()
        class ExceptionalError<T>(t: Throwable): Error<T>()
    }
}