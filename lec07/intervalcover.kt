import java.util.TreeMap

data class Interval(val s: Double, val t: Double, val id: Int)

fun main() {
    var buf = readLine()
    while (buf != null && buf.length > 0) {
        val (L, R) = buf.split(" ").map{ it.toDouble() }
        val n = readLine()!!.toInt()
        val intervals = (0 until n).map{
            val (x, y) = readLine()!!.split(" ").map{ it.toDouble() }
            Interval(x, y, it)
        }.sortedBy{ it.t }
        val ans = TreeMap<Double, Int>().apply {
            put(L-5e-7, 0)
            put(R+5e-7, n+1)
        }
        val bestID = TreeMap<Double, Int>()
        for ((x, y, id) in intervals) {
            val ceilX = ans.ceilingEntry(x-5e-7)
            if (x > R || y < L || ceilX == null || ceilX.key >= y) continue
            ans[y] = ceilX.value + 1
            bestID[y] = id
            var lowerY = ans.lowerEntry(y)
            while (lowerY != null && lowerY.value > ceilX.value) {
                ans.remove(lowerY.key)
                bestID.remove(lowerY.key)
                lowerY = ans.lowerEntry(y)
            }
        }
        if (ans.ceilingEntry(R)!!.value >= n+1) {
            println("impossible")
        } else {
            println(ans.ceilingEntry(R)!!.value)
            println(bestID.values.take(ans.ceilingEntry(R)?.value ?: 0).joinToString(" "){ "$it" })
        }
        buf = readLine()
    }
}