
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.arteaga.marlon.recipes.utils.TestsUtil
import org.junit.Rule
import org.junit.Test

class LoadingStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingStateVisible() {
        composeTestRule.setContent {
            LoadingState(isVisible = true)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.loading).assertIsDisplayed()
    }

    @Test
    fun testLoadingStateInvisible() {
        composeTestRule.setContent {
            LoadingState(isVisible = false)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.loading).assertDoesNotExist()
    }
}
