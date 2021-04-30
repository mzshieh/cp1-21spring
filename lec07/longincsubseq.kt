fun main() {
    var buf = readLine()
    while (buf != null && buf.length > 0) {
        val n = buf.toInt()
        val a = readLine()!!.split(" ").map{it.toLong()}
        val b = IntArray(n){-1}
        val lis = IntArray(n){1}
        val best = LongArray(1+n){ if (it==0) -(1L shl 32) else 1L shl 32 }
        val bestChoice = IntArray(1+n) { -1 }
        var ans = 0
        var ansIndex = 0
        for (i in 0 until n) {
            val first: (Long) -> Int = { v ->
                var pos = 0
                var step = 2
                while (step < n) step *= 2
                step /= 2
                while (step > 0) {
                    if (pos+step < n && best[pos+step] < v)
                        pos += step
                    step /= 2
                }
                pos + 1
            }
            val len = first(a[i])
            lis[i] = len
            b[i] = bestChoice[len - 1]
            best[len] = a[i]
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