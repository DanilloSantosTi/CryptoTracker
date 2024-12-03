package com.plcoding.cryptotracker.crypto.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinResponseDto(
    val data: List<CoinDto>
)
