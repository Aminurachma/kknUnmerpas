package com.example.kkn_unmer.network

import android.content.Context
import com.example.kkn_unmer.utils.logDebug
import com.example.kkn_unmer.utils.logError
import com.example.kkn_unmer.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException
import retrofit2.Response

class Network(private val coroutineScope: CoroutineScope) {

    fun <T> request(context: Context, response: suspend() -> Response<T>, onSuccess:(T?) -> Unit, onFinally:(Boolean) -> Unit) {
        coroutineScope.launch {
            try {
                val result = response()
               if (result.isSuccessful) {
                   logDebug("Network # isSuccessful")
                   logDebug("Network # url API : ${result.raw().request.url}")
                   onSuccess(result.body())
               } else {
                   logError("Network # UnSuccessFul")
                   logError("Network # url API : ${result.raw().request.url}")
                   logError("Network # code : ${result.code()}")
                   logError("Network # body : ${result.errorBody().toString()}")
                   context.showToast("UnSuccessFul # code : ${result.code()}")
               }
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) {
                    // coroutines has canceled
                    logError("Network # Throwable ==> job canceled")
                } else {
                    logError("Network # Throwable")
                    context.showToast("Throwable ${throwable.message})}")
                    throwable.printStackTrace()
                }
            } finally {
                logDebug("Network # finally")
                onFinally(true)
            }
        }
    }

}