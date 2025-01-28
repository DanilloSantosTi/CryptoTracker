package com.plcoding.cryptotracker.crypto.presentation.viewmodel

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreenViewProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinSplashViewModel : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _isReady.value = true
        }
    }

    fun zoomOut(splashScreenView: SplashScreenViewProvider){
        val zoomX = ObjectAnimator.ofFloat(
            splashScreenView.iconView,
            View.SCALE_X,
            0.4f,
            0.0f
        )

        zoomX.interpolator = OvershootInterpolator()
        zoomX.duration = 1000L
        zoomX.doOnEnd { splashScreenView.remove() }

        val zoomY = ObjectAnimator.ofFloat(
            splashScreenView.iconView,
            View.SCALE_Y,
            0.4f,
            0.0f
        )

        zoomY.interpolator = OvershootInterpolator()
        zoomY.duration = 1000L
        zoomY.doOnEnd { splashScreenView.remove() }

        zoomX.start()
        zoomY.start()
    }
}