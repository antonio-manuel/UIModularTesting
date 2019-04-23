package eu.antoniolopez.playground.api.instrumentation

import eu.antoniolopez.playground.exceptions.*
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            when (response.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> throw NotFoundException()
                HttpURLConnection.HTTP_FORBIDDEN -> throw ForbiddenException()
                HttpURLConnection.HTTP_INTERNAL_ERROR -> throw InternalErrorException()
                HttpURLConnection.HTTP_UNAUTHORIZED -> throw UnauthorizedException()
                HttpURLConnection.HTTP_CONFLICT -> throw ConflictException()
                HttpURLConnection.HTTP_BAD_REQUEST -> throw BadRequestException()
                else -> throw NetworkException()
            }
        }
        return response
    }
}


