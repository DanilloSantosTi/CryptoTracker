package com.plcoding.cryptotracker.core.data.networking

import com.plcoding.cryptotracker.core.NetworkStatus.HTTP_STATUS_OK_END
import com.plcoding.cryptotracker.core.NetworkStatus.HTTP_STATUS_OK_START
import com.plcoding.cryptotracker.core.NetworkStatus.HTTP_STATUS_REQUEST_TIMEOUT
import com.plcoding.cryptotracker.core.NetworkStatus.HTTP_STATUS_SERVER_ERROR_END
import com.plcoding.cryptotracker.core.NetworkStatus.HTTP_STATUS_SERVER_ERROR_START
import com.plcoding.cryptotracker.core.NetworkStatus.HTTP_STATUS_TOO_MANY_REQUESTS
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when (response.status.value) {
        in HTTP_STATUS_OK_START..HTTP_STATUS_OK_END -> {
            try {
                Result.Success(response.body<T>())
            } catch (noTransformationFoundException: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }

        HTTP_STATUS_REQUEST_TIMEOUT -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        HTTP_STATUS_TOO_MANY_REQUESTS -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in HTTP_STATUS_SERVER_ERROR_START..HTTP_STATUS_SERVER_ERROR_END -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}
