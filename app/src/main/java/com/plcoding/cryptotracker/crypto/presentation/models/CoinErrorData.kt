package com.plcoding.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CoinErrorData(
    @StringRes val titleError: Int,
    @StringRes val contentError: Int,
    @DrawableRes val imageError: Int
)
