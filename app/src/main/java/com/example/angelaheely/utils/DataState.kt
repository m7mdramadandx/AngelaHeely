package com.example.angelaheely.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

sealed class DataState<out T>(
    val loading: Boolean,
    private val data: T? = null,
    val code: Int = 200,
    val exception: Exceptions? = null
) {
    fun data(): T? = data

    object Idle : DataState<Nothing>(false)

    class Loading<T>(val cachedData: T? = null) : DataState<T>(
        loading = true,
        data = cachedData
    )

    data class Error<out T>(val e: Exceptions) : DataState<T>(loading = false, exception = e)

    data class Success<out T>(val data: T) : DataState<T>(
        loading = false,
        data = data
    )
}

fun <T> DataState<List<T>>.isEmptyList(): Boolean {
    return this is DataState.Success && this.data.isEmpty()
}

inline fun <reified T> Response<T>.handleResponse(): DataState<T> {

    return when (code()) {
        200 ->
            DataState.Success(responseConverter(this))

        401 ->
            DataState.Error(Exceptions.Unauthorized)

        500 ->
            DataState.Error(Exceptions.InternalServerError)

        400 ->
            DataState.Error(
                Exceptions.ErrorResponse(msg = this.message(), code = 400)
            )

        else -> {
            DataState.Error(
                Exceptions.ErrorResponse(msg = "Something Went Wrong", code = code())
            )
        }
    }
}

inline fun <reified T> responseConverter(
    response: Response<T>
): T {
    return try {
        val gson = GsonBuilder().serializeNulls().create()

        if (response.isSuccessful.not()) {
            var errorResponse = ErrorResponse()
            if (response.errorBody() != null) {
                try {
                    val obj = response.errorBody()?.string()?.let { JSONObject(it) }
                    try {
                        val temp = Gson().fromJson(obj.toString(), ErrorResponse::class.java)
                        temp?.let { errorResponse = it }
                    } catch (e: IllegalStateException) {
                        errorResponse.message = e.message
                        throw errorResponse
                    }
                } catch (e: JSONException) {
                    errorResponse.message = e.message
                    throw errorResponse
                }
            }

            errorResponse.code = response.code()
            errorResponse.message = response.message()
            throw errorResponse

        } else {

            val res = gson.toJson(response.body())
            val data = gson.fromJson(res, T::class.java)
            data
        }
    } catch (e: Exception) {
        throw e
    }
}

inline fun <reified T> Throwable.handleException(): DataState<T> {
    return when (this) {
        is HttpException ->
            DataState.Error(
                Exceptions.ErrorResponse(
                    msg = "Network Error"
                )
            )

        is UnknownHostException ->
            DataState.Error(
                Exceptions.ErrorResponse(
                    msg = "No Internet"
                )
            )

        else ->
            DataState.Error(
                Exceptions.ErrorResponse(
                    msg = "Something Went Wrong"
                )
            )
    }
}


sealed class Exceptions {
    object InternalServerError : Exceptions()
    object Unauthorized : Exceptions()
    data class ErrorResponse(val msg: String, val code: Int? = null) : Exceptions()
}


class ErrorResponse(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") override var message: String? = null,
) : Exception() {

    override fun toString(): String {
        var error = ""
        if (!message.isNullOrEmpty())
            error += message
        return error.trim()
    }
}