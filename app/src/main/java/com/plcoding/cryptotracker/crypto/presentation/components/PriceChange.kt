package com.plcoding.cryptotracker.crypto.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayedNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.greenBackground
import com.plcoding.cryptotracker.core.FloatNumbers.HUNDRED
import com.plcoding.cryptotracker.core.FloatNumbers.ZERO

@Composable
fun PriceChange(
    change: DisplayedNumber,
    modifier: Modifier = Modifier
) {
    val contentColor = if (change.value < ZERO) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        Color.Green
    }

    val backgroundColor = if (change.value < ZERO) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        greenBackground
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(HUNDRED))
            .background(backgroundColor)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (change.value < ZERO) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowUp
            },
            contentDescription = if (change.value < ZERO) {
                Icons.Default.KeyboardArrowDown.name
            } else {
                Icons.Default.KeyboardArrowUp.name
            },
            modifier = Modifier.size(20.dp),
            tint = contentColor
        )

        Text(
            text = stringResource(
                id = R.string.cyptotracker_change_percent_24h,
                change.formatted
            ),
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun PriceChangePreview() {
    CryptoTrackerTheme {
        PriceChange(
            change = DisplayedNumber(
                value = -2.43,
                formatted = "2.43"

            )
        )
    }
}