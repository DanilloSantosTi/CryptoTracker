package com.plcoding.cryptotracker.crypto.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.plcoding.cryptotracker.crypto.domain.models.Coin
import com.plcoding.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.plcoding.cryptotracker.crypto.presentation.components.lineChart.DataPoint
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayedNumber,
    val priceUsd: DisplayedNumber,
    val changePercent24H: DisplayedNumber,
    val coinPriceHistory: List<DataPoint> = emptyList(),
    @DrawableRes val iconRes: Int
)

data class DisplayedNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = this.id,
        rank = this.rank,
        name = this.name,
        symbol = this.symbol,
        marketCapUsd = this.marketCapUsd.toDisplayedNumber(),
        priceUsd = this.priceUsd.toDisplayedNumber(),
        changePercent24H = this.changePercent24H.toDisplayedNumber(),
        iconRes = getDrawableIdForCoin(this.symbol)
    )
}

fun Double.toDisplayedNumber(): DisplayedNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayedNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun absoluteChangeFormatted(price: Double, change24Hr: Double): DisplayedNumber {
    return ((price * change24Hr)/100).toDisplayedNumber()
}
