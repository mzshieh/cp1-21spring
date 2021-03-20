private class DisjointSets(val size: Int) {
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
    val (n, m) = readLine()!!.split(" ").map{ it.toInt() }
    val djs = DisjointSets(n+1)
    for (round in 1..m) {
        val (u, v) = readLine()!!.split(" ").map{ it.toInt() }
        djs.union(u, v)
    }
    if (djs.pop(1)==n) {
        println("Connected")
    } else {
        val buf = StringBuilder()
        for (v in 2..n) {
            if (djs.find(v)!=djs.find(1)) {
                buf.append("$v\n")
            }
        }
        print(buf)
    }
}