
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.arteaga.marlon.recipes.utils.TestsUtil
import org.junit.Rule
import org.junit.Test

class ErrorStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testErrorStateVisible() {
        composeTestRule.setContent {
            ErrorState(isVisible = true)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.errorImage).assertIsDisplayed()
    }

    @Test
    fun testErrorStateInvisible() {
        composeTestRule.setContent {
            ErrorState(isVisible = false)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.errorImage).assertDoesNotExist()
    }
}
