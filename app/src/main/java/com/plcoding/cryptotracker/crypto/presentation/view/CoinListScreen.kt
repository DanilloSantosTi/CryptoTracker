package com.plcoding.cryptotracker.crypto.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.crypto.presentation.components.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.components.ShimmerList
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListState

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        ShimmerList()
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}