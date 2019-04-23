package eu.antoniolopez.playground.api

import eu.antoniolopez.playground.exceptions.NetworkException
import retrofit2.Call
import timber.log.Timber
import java.io.IOException

abstract class BaseRetrofitApi {
    inline fun <T> execute(f: () -> Call<T>): T {
        val body: T
        try {
            body = f().execute().body()!!
        } catch (error: IOException) {
            throw NetworkException(error.message)
        }
        return body
    }

    inline fun <T> executeVoid(f: () -> Call<T>) {
        try {
            f().execute()
        } catch (error: IOException) {
            Timber.e(error)
            throw NetworkException()
        }
    }
}
