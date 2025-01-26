@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.plcoding.cryptotracker.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.core.presentation.util.ObserveAsEvents
import com.plcoding.cryptotracker.core.presentation.util.toDataError
import com.plcoding.cryptotracker.crypto.presentation.models.CoinErrorData
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListActions
import com.plcoding.cryptotracker.crypto.presentation.models.CoinListEvent
import com.plcoding.cryptotracker.crypto.presentation.view.CoinListDetailScreen
import com.plcoding.cryptotracker.crypto.presentation.view.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.view.ErrorScreen
import com.plcoding.cryptotracker.crypto.presentation.viewmodel.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

private lateinit var dataError: CoinErrorData

@Composable
fun AdaptiveCoinListDetailPane(
    viewModel: CoinListViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
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

    when {
        isError -> {
            ErrorScreen(
                modifier = Modifier.padding(innerPadding),
                data = dataError,
                onClick = viewModel::onAction
            )
        }

        else -> {
            val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
            NavigableListDetailPaneScaffold(
                navigator = navigator,
                listPane = {
                    AnimatedPane {
                        CoinListScreen(
                            state = state,
                            onClick = { action ->
                                viewModel.onAction(action)
                                when (action) {
                                    is CoinListActions.OnCoinClick -> {
                                        navigator.navigateTo(
                                            pane = ListDetailPaneScaffoldRole.Detail
                                        )
                                    }

                                    CoinListActions.OnRefresh -> TODO()
                                }
                            }
                        )
                    }
                },
                detailPane = {
                    AnimatedPane {
                        CoinListDetailScreen(state = state)
                    }
                },
                modifier = modifier
            )
        }
    }
}