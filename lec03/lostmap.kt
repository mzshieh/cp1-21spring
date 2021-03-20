data class Edge(val cost: Int, val u: Int, val v: Int)

private class DJS(val size: Int) {
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
    fun pop(x: Int): Int {
        return -arr[find(x)]
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val edges = List<List<Edge>>(n) { r ->
        readLine()!!.split(" ").mapIndexed{ c, v -> Edge(v.toInt(), r, c) }
    }.flatten().sortedBy{ it.cost }
    val djs = DJS(n)
    for ((c, u, v) in edges) {
        djs.apply {
            if (find(u) != find(v)) {
                println("${u+1} ${v+1}")
                union(u, v)
            }
        }
        if (djs.pop(0) == n) break
    }
}