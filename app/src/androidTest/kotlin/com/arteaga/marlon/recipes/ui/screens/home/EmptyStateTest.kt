
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.arteaga.marlon.recipes.utils.TestsUtil
import org.junit.Rule
import org.junit.Test

class EmptyStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmptyStateVisible() {
        composeTestRule.setContent {
            EmptyState(isVisible = true)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText(TestsUtil.emptyText).assertIsDisplayed()
    }

    @Test
    fun testEmptyStateInvisible() {
        composeTestRule.setContent {
            EmptyState(isVisible = false)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText(TestsUtil.emptyText).assertDoesNotExist()
    }
}
