package com.plcoding.cryptotracker.crypto.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.crypto.presentation.models.CoinErrorData

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    data: CoinErrorData
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(data.imageError),
            contentDescription = data.titleError
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = data.titleError)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = data.contentError)

    }
}