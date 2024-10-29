package com.plcoding.cryptotracker.crypto.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.plcoding.cryptotracker.crypto.presentation.animations.ShimmerAnimation

@Composable
fun ShimmerList() {
    ShimmerAnimation { brush ->
        LazyColumn {
            items(5) {
                CoinListItemShimmer(brush = brush)
            }
        }
    }
}