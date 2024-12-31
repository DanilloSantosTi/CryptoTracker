package com.plcoding.cryptotracker.core.presentation.util

import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.crypto.presentation.models.CoinErrorData

fun NetworkError.toDataError(): CoinErrorData {
    return when (this) {
        NetworkError.REQUEST_TIMEOUT -> {
            CoinErrorData(
                titleError = R.string.error_request_timeout,
                contentError = R.string.error_request_timeout_content,
                imageError = R.drawable.no_internet
            )
        }

        NetworkError.TOO_MANY_REQUESTS -> {
            CoinErrorData(
                titleError = R.string.error_too_many_request,
                contentError = R.string.error_too_many_request_content,
                imageError = R.drawable.no_internet
            )
        }


        NetworkError.NO_INTERNET -> {
            CoinErrorData(
                titleError = R.string.error_no_internet,
                contentError = R.string.error_no_internet_content,
                imageError = R.drawable.no_internet
            )
        }

        NetworkError.SERVER_ERROR -> {
            CoinErrorData(
                titleError = R.string.error_unknown,
                contentError = R.string.error_unknown_content,
                imageError = R.drawable.no_internet
            )
        }

        NetworkError.SERIALIZATION -> {
            CoinErrorData(
                titleError = R.string.error_serialization,
                contentError = R.string.error_serialization_content,
                imageError = R.drawable.no_internet
            )
        }

        NetworkError.UNKNOWN -> {
            CoinErrorData(
                titleError = R.string.error_unknown,
                contentError = R.string.error_unknown_content,
                imageError = R.drawable.no_internet
            )
        }
    }
}
