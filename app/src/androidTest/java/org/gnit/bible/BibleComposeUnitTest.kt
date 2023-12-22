package org.gnit.bible

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BibleComposeUnitTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGetBooksFromTranslation(){
        composeTestRule.setContent {
            //TODO add tests for composable
        }
    }
}
