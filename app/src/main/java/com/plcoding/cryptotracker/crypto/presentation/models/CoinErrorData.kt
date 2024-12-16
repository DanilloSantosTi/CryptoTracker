package com.plcoding.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes

data class CoinErrorData(
    val titleError: String,
    val contentError: String,
    @DrawableRes val imageError: Int
)
