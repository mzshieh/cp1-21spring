import java.util.TreeMap

fun main() {
    var buf = readLine()
    while (buf != null && buf.length > 0) {
        val n = buf.toInt()
        val a = readLine()!!.split(" ").map{it.toLong()}
        val b = IntArray(n){-1}
        val lis = IntArray(n){1}
        val best = TreeMap<Long,Int>().apply{
            put(-(1L shl 32), 0)
            put(1L shl 32, n+1)
        }
        val bestChoice = IntArray(1+n){-1}
        var ans = 0
        var ansIndex = 0
        for (i in 0 until n) {
            val len = best.lowerEntry(a[i]).value + 1
            lis[i] = len
            b[i] = bestChoice[len - 1]
            while( best.higherEntry(a[i]).value <= len )
                best.remove(best.higherKey(a[i]))
            best[a[i]] = len
            bestChoice[len] = i
            if (ans < len) {
                ans = len
                ansIndex = i
            }
        }
        val result = ArrayList<Int>()
        while (ansIndex >= 0) {
            result.add(ansIndex)
            ansIndex = b[ansIndex]
        }
        println(ans)
        println(result.reversed().joinToString(" ") {it.toString()})
        buf = readLine()
    }
}