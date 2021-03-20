fun main() {
    val n = readLine()!!.toInt()
    readLine()!!.split(" ")
        .map { it.toInt() }
        .sortedDescending()
        .withIndex()
        .map { (i, v) -> i + v }
        .min()
        .let { println(minOf(it ?: 0, n)) }
}