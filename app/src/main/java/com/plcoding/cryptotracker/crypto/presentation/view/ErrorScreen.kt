package com.plcoding.cryptotracker.crypto.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.components.CoinText
import com.plcoding.cryptotracker.crypto.presentation.models.CoinErrorData

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    data: CoinErrorData,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(85.dp),
                painter = painterResource(data.imageError),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(data.titleError)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CoinText(
                text = stringResource(data.titleError),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            CoinText(
                text = stringResource(data.contentError),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
            )
        }

        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick,
                shape = Shapes().large
            ) {
                Text(text = stringResource(R.string.cyptotracker_button_title))
            }
        }
    }
}
