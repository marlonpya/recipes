
package com.arteaga.marlon.recipes.ui.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.arteaga.marlon.domain.fake.RecipeTestData
import com.arteaga.marlon.recipes.utils.TestsUtil
import org.junit.Rule
import org.junit.Test

class ItemInformationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testItemInformationStateVisible() {
        composeTestRule.setContent {
            ItemInformation(RecipeTestData.description, Icons.Default.Favorite)
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagDetailScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.itemInformation).assertIsDisplayed()
    }
}
