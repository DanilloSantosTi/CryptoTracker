package com.plcoding.cryptotracker.crypto.presentation.models

sealed interface CoinListActions {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListActions
    data object OnRefresh : CoinListActions
}