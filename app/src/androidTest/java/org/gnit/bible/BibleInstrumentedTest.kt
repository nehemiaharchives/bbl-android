package org.gnit.bible

import android.util.SparseArray
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.serialization.json.Json
import org.gnit.bible.lib.SparseArraySerializer
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BibleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.gnit.bible", appContext.packageName)
    }

    @Test
    fun testSparseArraySerializer(){
        val json = """
            {
                "1": "Genesis",
                "2": "Exodus"
            }
        """.trimIndent()

        val sparseArray = SparseArray<String>(2)
        sparseArray.put(1, "Genesis")
        sparseArray.put(2, "Exodus")

        // encode SparseArray to Json
        val pJson = Json {
            prettyPrint = true
        }
        val encoded = pJson.encodeToString(SparseArraySerializer, sparseArray)

        assertEquals(json, encoded)

        // decode Json to SparseArray
        val decoded = pJson.decodeFromString(SparseArraySerializer, json)
        assertEquals(sparseArray.toString(), decoded.toString())

        // decode actual json in assets folder, the location of the json file is assets/webus/webus.books.json
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appContext.assets.open("webus/webus.books.json").bufferedReader().use { reader ->
            val webusJson = reader.readText()
            val decodedWebusBooks = pJson.decodeFromString(SparseArraySerializer, webusJson)
            assertEquals("Genesis", decodedWebusBooks[1])
        }

        appContext.assets.open("jc/jc.books.json").bufferedReader().use { reader ->
            val jcJson = reader.readText()
            val decodedJcBooks = pJson.decodeFromString(SparseArraySerializer, jcJson)
            assertEquals("創世記", decodedJcBooks[1])
        }
    }
}