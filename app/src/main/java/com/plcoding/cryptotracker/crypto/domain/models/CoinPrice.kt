package com.plcoding.cryptotracker.crypto.domain.models

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)
