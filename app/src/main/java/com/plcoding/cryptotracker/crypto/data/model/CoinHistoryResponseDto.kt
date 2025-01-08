package com.plcoding.cryptotracker.crypto.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryResponseDto(
    val data: List<CoinPriceDto>
)
