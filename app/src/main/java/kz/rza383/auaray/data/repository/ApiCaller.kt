package kz.rza383.auaray.data.repository

import android.widget.Button
import kz.rza383.domain.entity.Result
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ApiCaller @Inject constructor() {

    fun <T>call(response: Response<T>): Result<T>{
        return try {
            when {
                response.isSuccessful ->{
                    Result.Success(response.body())
                }
                else -> {
                    Result.Error.ApiError(response.code(), response.message())
                }
            }
        }
        catch(i: IOException){
           Result.Error.NetworkError()
        }
        catch (t: Throwable) {
            Result.Error.ExceptionalError(t)
        }
    }
}

fun <T> Result<T>.onSuccess(action: (T?) -> Unit): Result<T>{
    if(this is Result.Success) action(this.data)
    return this
}

fun <T> Result<T>.onError(action: () -> Unit): Result<T> {
    if (this is Result.Error<T>) action()
    return this
}


suspend fun <T> Result<T>.onSuccessSuspend(action: suspend (T?) -> Unit): Result<T>{
    if(this is Result.Success) action(this.data)
    return this
}

suspend fun <T> Result<T>.onErrorSuspend(action: suspend () -> Unit): Result<T> {
    if (this is Result.Error<T>) action()
    return this
}

fun Button.clickWithDebounce(click: () -> Unit){
    this.setOnClickListener {
        click
    }
}