package com.sakiwei.compose_test.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
class CommonButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verifyTitle() {
        with(composeTestRule) {
            setContent {
                CommonButton(title = "My title")
            }
            onNodeWithText("My title").assertIsDisplayed()
        }
    }


    @Test
    fun verifyOnClick() {
        with(composeTestRule) {
            setContent {
                var title by remember {
                    mutableStateOf("My title")
                }
                CommonButton(title = title) {
                    title = "New title"
                }
            }
            onNodeWithText("My title").assertIsDisplayed().performClick()
            onNodeWithText("New title").assertIsDisplayed()
        }
    }
}