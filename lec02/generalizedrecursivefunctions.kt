import java.math.BigInteger

fun main() {
    val nCases = readLine()!!.toInt()
    for (testCase in 1..nCases) {
        val firstLine = readLine()!!.split(" ")
        val secondLine = readLine()!!.split(" ")
        val n = (firstLine.size - 2) / 2
        val a = (0 until n).map{ firstLine[it*2].toInt() }
        val b = (0 until n).map{ firstLine[it*2 + 1].toInt() }
        val c = firstLine[2*n].toBigInteger()
        val d = firstLine[2*n+1].toBigInteger()
        val m = secondLine.size / 2
        val x = (0 until m).map{ secondLine[it*2].toInt() }
        val y = (0 until m).map{ secondLine[it*2 + 1].toInt() }
        val table = HashMap<Pair<Int,Int>,BigInteger>()
        fun f(xv: Int, yv: Int): BigInteger {
            if( xv <= 0 || yv <= 0 ) return d
            if( n == 0 ) return c
            return table.getOrPut(Pair(xv, yv)) {
                c + (0 until n).map{ f(xv-a[it], yv-b[it]) }.reduce{ p, q -> p+q }
            }
        }
        for (i in 0 until m) {
            println(f(x[i], y[i]))
        }
        println("")
    }
}