package com.plcoding.cryptotracker.crypto.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.components.CoinText
import com.plcoding.cryptotracker.crypto.presentation.components.InfoCard
import com.plcoding.cryptotracker.crypto.presentation.components.previewCoin
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.models.absoluteChangeFormatted
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinListDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    if (state.selectedCoin != null) {
        val coin = state.selectedCoin

        val changePercent24HIsPositive = coin.changePercent24H.value > 0.0

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = coin.iconRes
                ),
                contentDescription = coin.name,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            CoinText(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            CoinText(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    title = stringResource(id = R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.stock)
                )

                InfoCard(
                    title = stringResource(id = R.string.price),
                    formattedText = "$ ${coin.priceUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.dollar)
                )

                InfoCard(
                    title = stringResource(id = R.string.change_last_24h),
                    formattedText = absoluteChangeFormatted(
                        price = coin.priceUsd.value,
                        change24Hr = coin.changePercent24H.value
                    ).formatted,
                    icon = contentIconChangeLast24Hr(changePercent24HIsPositive),
                    contentColor = contentColorChangeLast24Hr(changePercent24HIsPositive)
                )
            }
        }
    }
}

@Composable
private fun contentIconChangeLast24Hr(isPositive: Boolean): ImageVector {
    return if (isPositive) {
        ImageVector.vectorResource(id = R.drawable.trending)
    } else {
        ImageVector.vectorResource(id = R.drawable.trending_down)
    }
}

@Composable
private fun contentColorChangeLast24Hr(isPositive: Boolean): Color {
    return if (isPositive) {
        if (isSystemInDarkTheme()) Color.Green else greenBackground
    } else {
        MaterialTheme.colorScheme.error
    }
}

@PreviewLightDark
@Composable
private fun CoinListDetailScreenPreview() {
    CryptoTrackerTheme {
        CoinListDetailScreen(
            state = CoinListState(
                selectedCoin = previewCoin
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}
