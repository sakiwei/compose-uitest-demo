package com.sakiwei.compose_test.viewModels

import com.sakiwei.compose_test.rules.MainDispatcherRule
import com.sakiwei.compose_test.screens.LandingUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LandingViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun initStartLoading() = runTest {
        val viewModel = LandingViewModel()
        assertEquals(viewModel.state.value, LandingUiState.Loading)
        advanceUntilIdle()
        assertEquals(viewModel.state.value, LandingUiState.Loaded(0))
    }

    @Test
    fun countIncrement() = runTest {
        val viewModel = LandingViewModel()
        advanceUntilIdle()
        assertEquals(viewModel.state.value, LandingUiState.Loaded(0))
        viewModel.countIncrement()
        assertEquals(viewModel.state.value, LandingUiState.Loaded(1))
    }
}