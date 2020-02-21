import java.io.File
import kotlin.random.Random

fun main() {
    generate()
    findTopNElements(50).also(::println)
}

private fun findTopNElements(numberOfElements: Int): List<Int> {
    val bufferedReader = Application::class.java.getResourceAsStream("input.txt").bufferedReader()
    val topItems = mutableListOf<Int>()

    var int = bufferedReader.readLine().toInt()
    while (true) {
        val appropriateIndexToInsert = getAppropriateIndexToInsert(int, topItems, numberOfElements)
        when {
            appropriateIndexToInsert >= topItems.size -> topItems.add(int)
            appropriateIndexToInsert != -1 -> topItems[appropriateIndexToInsert] = int
        }
        val newLine = bufferedReader.readLine() ?: break
        int = newLine.toInt()
    }

    return topItems.sorted()
}

private fun generate() {
    val random = Random(5)
    repeat(500) {
        val fileWriter = File(Application::class.java.getResource("input.txt").path)
        fileWriter.appendText(random.nextInt(1, 1000000).toString() + "\n")
    }
}

private fun getAppropriateIndexToInsert(x: Int, list: MutableList<Int>, numberOfElements: Int): Int {
    val smallest = list.fold(Int.MAX_VALUE) { acc, current -> if (acc > current) current else acc }
    return when {
        smallest == Int.MAX_VALUE -> 0
        list.size < numberOfElements -> list.size
        x > smallest -> list.indexOf(smallest)
        else -> -1
    }
}

class Application