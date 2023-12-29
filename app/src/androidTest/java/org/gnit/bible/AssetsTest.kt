package org.gnit.bible

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AssetsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun getAssetTranslationsTest() {
        composeTestRule.setContent {

            val expected = listOf(
                Translation.webus,
                Translation.kjv,
                Translation.cunp,
                Translation.krv,
                Translation.jc
            )
            assertEquals(expected, getAssetTranslations())
        }
    }
}