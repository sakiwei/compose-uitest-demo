@file:OptIn(ExperimentalMaterial3Api::class)

package com.sakiwei.compose_test.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sakiwei.compose_test.ui.theme.ComposetestTheme
import com.sakiwei.compose_test.viewModels.LandingViewModel
import com.sakiwei.compose_test.views.CommonButton

sealed class LandingUiState {
    data object Initial : LandingUiState()
    data object Loading : LandingUiState()
    data class Loaded(val count: Int = 0) : LandingUiState()
}

@Composable
fun LandingScreen(landingViewModel: LandingViewModel = viewModel()) {
    val state by landingViewModel.state.collectAsState()
    LandingScreenContent(state, landingViewModel::countIncrement)
}

@ExperimentalMaterial3Api
@Composable
fun LandingScreenContent(
    uiState: LandingUiState = LandingUiState.Initial,
    countIncrement: () -> Unit = {},
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("TopAppBar") },
        )
    }, content = { paddingValues ->
        Surface(Modifier.padding(paddingValues)) {
            Column(Modifier.padding(horizontal = 16.dp)) {
                when (uiState) {
                    LandingUiState.Initial -> { /* no implementation needed */
                    }

                    is LandingUiState.Loaded -> {
                        CommonButton(
                            "Count: ${uiState.count}",
                            onClick = countIncrement
                        )
                    }

                    LandingUiState.Loading -> Text("Loading...")
                }
            }
        }
    })
}

private class LandingUiStateProvider : PreviewParameterProvider<LandingUiState> {
    override val values = sequenceOf(
        LandingUiState.Initial,
        LandingUiState.Loading,
        LandingUiState.Loaded(count = 1),
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_LandingScreen(
    @PreviewParameter(LandingUiStateProvider::class) state: LandingUiState,
) {
    ComposetestTheme {
        LandingScreenContent(state)
    }
}