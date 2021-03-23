private class Scanner {
    val lines = java.io.InputStreamReader(System.`in`).readLines()
    var curLine = 0
    var st = java.util.StringTokenizer(lines[0])
    fun next(): String {
        while(!st.hasMoreTokens())
            st = java.util.StringTokenizer(lines[++curLine])
        return st.nextToken()
    }
    fun nextInt() = next().toInt()
}

private class AlmostDisjointSets(val size: Int, val maxOp: Int) {
    val n = size + maxOp + 1
    private val arr = IntArray(n){ -1 }
    val pop = IntArray(n){ if (it <= size) 1 else 0 }
    val sum = LongArray(n){ if (it <= size) it.toLong() else 0L }
    val pos = IntArray(1+size){ it }
    var alloc = size + 1
    fun find(x: Int): Int {
        if (arr[x] < 0) return x
        return find(arr[x]).also{ arr[x] = it }
    }
    fun union(x: Int, y: Int) {
        val px = find(x)
        val py = find(y)
        if (px == py) return
        if (arr[px] > arr[py]) {
            arr[py] += arr[px]
            pop[py] += pop[px]
            sum[py] += sum[px]
            arr[px] = py
        } else {
            arr[px] += arr[py]
            pop[px] += pop[py]
            sum[px] += sum[py]
            arr[py] = px
        }
    }
    fun move(x: Int, y: Int) {
        val px = find(pos[x])
        val py = find(pos[y])
        if (px == py) return
        pop[px]--
        sum[px] -= x.toLong()
        pop[alloc] = 1
        sum[alloc] = x.toLong()
        pos[x] = alloc++
        union(pos[x], py)
    }
}

fun main() {
    val sc = Scanner()
    var n = 0
    var m = 0
    val buf = StringBuilder()
    while (try {
            n = sc.nextInt()
            m = sc.nextInt()
            true
        } catch (e: Exception) {
            false
        }) {
        val adjs = AlmostDisjointSets(n, m)
        for (round in 1..m) {
            when (sc.nextInt()) {
                1 -> {
                    val x = sc.nextInt()
                    val y = sc.nextInt()
                    adjs.apply {
                        union(pos[x], pos[y])
                    }
                }
                2 -> {
                    val x = sc.nextInt()
                    val y = sc.nextInt()
                    adjs.move(x, y)
                }
                else -> {
                    val x = sc.nextInt()
                    adjs.apply {
                        val px = find(pos[x])
                        buf.append("${pop[px]} ${sum[px]}\n")
                    }
                }
            }
        }
    }
    print(buf)
}