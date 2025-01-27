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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.components.CoinText
import com.plcoding.cryptotracker.crypto.presentation.models.CoinErrorData
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListActions
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    data: CoinErrorData,
    onClick: (CoinListActions) -> Unit
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
                modifier = Modifier.size(100.dp),
                painter = painterResource(data.imageError),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(data.titleError)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CoinText(
                text = stringResource(data.titleError),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(32.dp))

            CoinText(
                text = stringResource(data.contentError),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Justify
            )
        }

        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick(CoinListActions.OnRefresh) },
                shape = Shapes().large
            ) {
                Text(text = stringResource(R.string.cyptotracker_button_title))
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ErrorScreenPreview() {
    CryptoTrackerTheme {
        ErrorScreen(
            data = ErrorDataPreview()
        ) { }
    }
}

private fun ErrorDataPreview(): CoinErrorData {
    return CoinErrorData(
        titleError = R.string.error_no_internet,
        contentError = R.string.error_no_internet_content,
        imageError = R.drawable.no_internet
    )
}
