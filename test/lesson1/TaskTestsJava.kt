package lesson1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Tag
import kotlin.test.Test

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("Easy")
    fun testSortTimes() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortAddresses() {
        JavaTasks.sortAddresses("input/addr_in2.txt", "tmp")
        assertFileContent("tmp",
                """
                    Железнодорожная 3 - Петров Иван
                    Железнодорожная 7 - Иванов Александр, Иванов Алексей, Иванов Михаил, Иванова Яна
                    Садовая 5 - Сидоров Петр, Сидорова Мария
                """.trimIndent()
        )
        File("tmp").delete()
    }

    @Test
    @Tag("Normal")
    fun testSortTemperatures() {
        val wr = File("temper").bufferedWriter()
        val random = Random()
        (1..1000).forEach { i ->
            val count = random.nextInt(7730) - 2730
            wr.write("${abs(count) / 10}.${abs(count) % 10}")
            wr.newLine()
            wr.flush()
        }
        JavaTasks.sortTemperatures("temper", "tmp")
        var a1: Double
        var a2 = -273.1
        File("tmp").bufferedReader().use {
            a1 = it.readLine().toDouble()
            if (a2 > a1) fail("Error. $a1 > $a2")
            a2 = a1
        }
        File("temper").delete()
        File("tmp").delete()
    }

    @Test
    @Tag("Normal")
    fun testSortSequence() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
    }

    @Test
    @Tag("Easy")
    fun testMergeArrays() {
        val result = arrayOf(null, null, null, null, null, 1, 3, 9, 13, 18, 23)
        JavaTasks.mergeArrays<Int>(arrayOf(4, 9, 15, 20, 23), result)
        assertArrayEquals(arrayOf(1, 3, 4, 9, 9, 13, 15, 18, 20, 23, 23), result)

        run {
            val (first, second, expectedResult) = generateArrays(20000, 20000)
            JavaTasks.mergeArrays<Int>(first, second)
            assertArrayEquals(expectedResult, second)
        }

        run {
            val (first, second, expectedResult) = generateArrays(500000, 500000)
            JavaTasks.mergeArrays<Int>(first, second)
            assertArrayEquals(expectedResult, second)
        }
    }
}