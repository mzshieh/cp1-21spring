class ScannerＥ {
    val lines = java.io.InputStreamReader(System.`in`).readLines()
    var curLine = 0
    var st = java.util.StringTokenizer(lines[0])
    fun next(): String {
        while (!st.hasMoreTokens())
            st = java.util.StringTokenizer(lines[++curLine])
        return st.nextToken()
    }
    fun nextInt() = next().toInt()
}

fun main() {
    val sc = ScannerＥ()
    val testCases = sc.nextInt()
    for (round in 1..testCases) {
        val prefixCount = HashMap<Int, Int>().apply{ put(0, 1) }
        var acc = 0
        var ans = 0L
        val n = sc.nextInt()
        for (i in 0 until n) {
            val v = sc.nextInt()
            acc += v
            ans += prefixCount[acc-47]?:0
            prefixCount[acc] = prefixCount[acc]?.plus(1) ?: 1
        }
        println(ans)
    }
}