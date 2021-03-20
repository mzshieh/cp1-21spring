import java.io.InputStreamReader
import java.util.*

private class Scan {
    val lines = InputStreamReader(System.`in`).readLines()
    var cur = 0
    var st = StringTokenizer(lines[0])
    fun next(): String {
        while(!st.hasMoreTokens())
            st = StringTokenizer(lines[++cur])
        return st.nextToken()
    }
    fun nextInt() = next().toInt()
}

private class UnionFind(val size: Int) {
    private val arr = IntArray(size) { -1 }
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
            arr[px] = py
        } else {
            arr[px] += arr[py]
            arr[py] = px
        }
    }
}

fun main() {
    val sc = Scan()
    val n = sc.nextInt()
    val m = sc.nextInt()
    val djs = UnionFind(n)
    val buf = StringBuilder()
    for (round in 1..m) {
        val op = sc.next()
        val x = sc.nextInt()
        val y = sc.nextInt()
        if (op[0] == '?') {
            buf.append(if (djs.find(x.toInt())==djs.find(y.toInt())) "yes\n" else "no\n")
        } else {
            djs.union(x.toInt(), y.toInt())
        }
    }
    print(buf)
}