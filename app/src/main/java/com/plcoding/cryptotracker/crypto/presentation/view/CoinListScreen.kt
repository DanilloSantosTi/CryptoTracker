package com.plcoding.cryptotracker.crypto.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.core.presentation.Numbers.SEVEN
import com.plcoding.cryptotracker.crypto.presentation.components.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.components.CoinListItemShimmer
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListActions
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.viewmodel.CoinListViewModel
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
    onClick: (CoinListActions) -> Unit
) {
    if (state.isLoading) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SEVEN) {
                CoinListItemShimmer(
                    Modifier.background(
                        MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {
                        onClick(CoinListActions.OnCoinClick(coinUi))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    HorizontalDivider()
}

@PreviewLightDark
@Composable
fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(
                isLoading = true
            ),
            onClick = {}
        )
    }
}
