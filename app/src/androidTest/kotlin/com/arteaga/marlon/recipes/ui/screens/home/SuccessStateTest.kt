
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.arteaga.marlon.domain.fake.RecipeTestData
import com.arteaga.marlon.recipes.utils.TestsUtil
import org.junit.Rule
import org.junit.Test

class SuccessStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSuccessStateVisible() {
        composeTestRule.setContent {
            SuccessState(
                isVisible = true,
                recipes = listOf(RecipeTestData.create()),
                goToDetail = {}
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeContent).assertIsDisplayed()
    }

    @Test
    fun testSuccessStateInvisible() {
        composeTestRule.setContent {
            SuccessState(
                isVisible = false,
                recipes = listOf(RecipeTestData.create()),
                goToDetail = {}
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeContent).assertDoesNotExist()
    }

    @Test
    fun testSuccessToMaps() {
        composeTestRule.setContent {
            SuccessState(
                isVisible = true,
                recipes = listOf(RecipeTestData.create()),
                goToDetail = {}
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeContent)
            .performClick()
            .assertExists()
    }
}
