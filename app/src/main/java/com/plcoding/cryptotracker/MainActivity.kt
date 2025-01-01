package com.plcoding.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.core.presentation.util.ObserveAsEvents
import com.plcoding.cryptotracker.core.presentation.util.toDataError
import com.plcoding.cryptotracker.crypto.presentation.models.CoinErrorData
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListActions
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListEvent
import com.plcoding.cryptotracker.crypto.presentation.view.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.view.ErrorScreen
import com.plcoding.cryptotracker.crypto.presentation.viewmodel.CoinListViewModel
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private lateinit var dataError: CoinErrorData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    var isError by rememberSaveable {
                        mutableStateOf(false)
                    }
                    ObserveAsEvents(viewModel.events) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                isError = true
                                dataError = event.error.toDataError()
                            }
                        }
                    }
                    if (isError) {
                        ErrorScreen(
                            modifier = Modifier.padding(innerPadding),
                            data = dataError
                        ) {
                            viewModel.onAction(CoinListActions.OnRefresh)
                        }
                    } else {
                        CoinListScreen(
                            state = state,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
