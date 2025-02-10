
package com.arteaga.marlon.recipes.ui.screens.maps

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.arteaga.marlon.domain.fake.RecipeTestData
import com.arteaga.marlon.recipes.utils.TestsUtil
import org.junit.Rule
import org.junit.Test

class MapsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMapsScreen() {
        val recipe = RecipeTestData.create()

        composeTestRule.setContent {
            MapsScreen(recipe = recipe)
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagMapScreen)

        composeTestRule
            .onNodeWithText(recipe.name)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription(TestsUtil.googleMap)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(TestsUtil.changeMapType)
            .assertIsDisplayed()
    }
}
