package com.plcoding.cryptotracker.crypto.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.core.presentation.LabelAnimation.FLOAT_ANIMATION
import com.plcoding.cryptotracker.core.presentation.LabelAnimation.INFINITE_TRANSITION

@Composable
fun CoinListItemShimmer(
    modifier: Modifier = Modifier
){
    val infiniteTransition = rememberInfiniteTransition(
        label = INFINITE_TRANSITION
    )
    val shimmerAlpha = infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = FLOAT_ANIMATION
    )

    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(85.dp)
                .background(Color.Gray.copy(alpha = shimmerAlpha.value))
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .background(Color.Gray.copy(alpha = shimmerAlpha.value))
            )

            Spacer(modifier = Modifier.size(4.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .background(Color.Gray.copy(alpha = shimmerAlpha.value))
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .background(Color.Gray.copy(alpha = shimmerAlpha.value))
            )
            Spacer(modifier = Modifier.size(8.dp))
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .background(Color.Gray.copy(alpha = shimmerAlpha.value))
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListItemShimmerPreview(){
    CryptoTrackerTheme {
        CoinListItemShimmer(
            Modifier.background(
                MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}
