package org.gnit.bible

import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class TranslationSerializeTest {
    @Test
    fun bibleStateSerializeTest(){

        val json = Json { encodeDefaults = true }

        val translation = Translation.webus
        assertEquals("""
            "webus"
        """.trimIndent(), json.encodeToString(translation))

        val bibleState = BibleState()
        val string = json.encodeToString(bibleState)
        assertEquals("""
            {"mainTranslation":"webus","subTranslation":null,"readingMode":"SINGLE","book":1,"chapter":1,"fontSize":16,"isZebraBackground":false,"spaceBetweenVerses":1,"isFontFamilySerif":true}
        """.trimIndent(), string)
    }
}