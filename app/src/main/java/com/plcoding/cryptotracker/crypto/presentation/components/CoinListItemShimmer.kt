package com.plcoding.cryptotracker.crypto.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
internal fun CoinListItemShimmer(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .size(85.dp)
                .background(brush)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(85.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(85.dp)
                    .background(brush)
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(85.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(85.dp)
                    .background(brush)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListItemShimmerPreview() {
    ShimmerList()
}