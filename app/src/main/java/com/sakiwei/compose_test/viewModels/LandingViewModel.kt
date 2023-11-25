package com.sakiwei.compose_test.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakiwei.compose_test.screens.LandingUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LandingViewModel : ViewModel() {
    private val _state = MutableStateFlow<LandingUiState>(LandingUiState.Initial)
    val state = _state.asStateFlow()

    init {
        start()
    }

    private fun start() {
        viewModelScope.launch {
            _state.update {
                LandingUiState.Loading
            }
            delay(1000L)
            _state.update {
                LandingUiState.Loaded(0)
            }
        }
    }

    fun countIncrement() {
        viewModelScope.launch {
            val state = _state.value
            if (state is LandingUiState.Loaded) {
                _state.update {
                    val newCount = state.count + 1
                    state.copy(newCount)
                }
            }
        }
    }
}