fun main() {
    val n = readLine()!!.toInt()
    val ans = IntArray(n) {
        readLine()!!
            .split(" ")
            .map{ it.toInt() }
            .reduce{ x, y -> x or y}
    }
    println(ans.joinToString(" "))
}