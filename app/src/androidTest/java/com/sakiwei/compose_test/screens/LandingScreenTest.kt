package com.sakiwei.compose_test.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sakiwei.compose_test.ui.theme.ComposetestTheme
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
class LandingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verifyCountValueOnClick() {
        with(composeTestRule) {
            setContent {
                LandingScreen(viewModel())
            }
            onNodeWithText("Loading...").assertIsDisplayed()
            waitUntilDoesNotExist(hasText("Loading..."), 1100L)
            onNodeWithText("Count: 0").performClick()
            onNodeWithText("Count: 1").assertIsDisplayed()
        }
    }
}