package com.plcoding.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.plcoding.cryptotracker.core.navigation.AdaptiveCoinListDetailPane
import com.plcoding.cryptotracker.crypto.presentation.viewmodel.CoinSplashViewModel
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CoinSplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isReady.value
            }
            setOnExitAnimationListener { splashScreenView ->
                viewModel.zoomOut(splashScreenView)
            }
        }
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdaptiveCoinListDetailPane(
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

