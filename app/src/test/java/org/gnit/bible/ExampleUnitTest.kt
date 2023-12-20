package org.gnit.bible

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addEmptyEntryToMakeSameSizeTest() {
        val listA = listOf("a", "b", "c")
        val listB = listOf("A", "B", "C", "D")

        val pair = addEmptyEntryToMakeSameSize(listA, listB)

        val newListA = pair.first
        val newListB = pair.second

        assertEquals(listOf("a", "b", "c", ""), pair.first)
        assertEquals(listOf("A", "B", "C", "D"), pair.second)
        assertEquals("", newListA.zip(newListB).last().first)

        // test opposite case

        val listA2 = listOf("1", "2", "3", "4")
        val listB2 = listOf("i", "ii", "iii")

        val pair2 = addEmptyEntryToMakeSameSize(listA2, listB2)

        val newListA2 = pair2.first
        val newListB2 = pair2.second

        assertEquals(listOf("1", "2", "3", "4"), pair2.first)
        assertEquals(listOf("i", "ii", "iii", ""), pair2.second)
        assertEquals("", newListA2.zip(newListB2).last().second)
    }
}