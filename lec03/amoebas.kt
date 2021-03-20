private class DJSets(val size: Int) {
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
    val (n,m) = readLine()!!.split(" ").map{ it.toInt() }
    val cells = List<String>(n){ readLine()!! }
    val grid: (Int, Int) -> Boolean = { x, y ->
        x in 0 until n && y in 0 until m && cells[x][y] == '#'
    }
    val pos = { x: Int, y: Int ->
        (x+1) * m + (y+1)
    }
    val djs = DJSets((n+2) * (m+2) )
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (!grid(i, j)) continue
            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (grid(i+dx, j+dy)) {
                        djs.union(pos(i,j),pos(i+dx,j+dy))
                    }
                }
            }
        }
    }
    var ans = 0
    for (i in 0 until n)
        for (j in 0 until m)
            if (grid(i,j) && pos(i,j) == djs.find(pos(i,j)))
                ans ++
    println(ans)
}