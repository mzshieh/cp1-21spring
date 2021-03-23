data class GridCell(val cost: Int, val r: Int, val c: Int)

private class DisjointSet(val size: Int) {
    val arr = IntArray(size) { -1 }
    val count = BooleanArray(size) { true }
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
    val (c, r) = readLine()!!.split(" ").map{ it.toInt() }
    val grid = List<List<Int>>(r){ readLine()!!.split(" ").map{ it.toInt() }}
    val cells = List<List<GridCell>>(r){ row ->
        grid[row].mapIndexed{ col, v ->
            GridCell(v, row, col) }
    }.flatten().sortedBy{ it.cost }
    val dsjs = DisjointSet(r*c )
    val node = {x: Int, y: Int ->
        x*c + y
    }
    for ((_, row, col) in cells) {
        if (row in 1 until r && grid[row-1][col] == grid[row][col])
            dsjs.union(node(row, col), node(row-1, col))
        if (row in 0 until r-1 && grid[row+1][col] == grid[row][col])
            dsjs.union(node(row, col), node(row+1, col))
        if (col in 1 until c && grid[row][col-1] == grid[row][col])
            dsjs.union(node(row, col), node(row, col-1))
        if (col in 0 until c-1 && grid[row][col+1] == grid[row][col])
            dsjs.union(node(row, col), node(row, col+1))
    }
    for ((_, row, col) in cells) {
        if (row in 1 until r && grid[row-1][col] < grid[row][col])
            dsjs.count[dsjs.find(node(row, col))] = false
        if (row in 0 until r-1 && grid[row+1][col] < grid[row][col])
            dsjs.count[dsjs.find(node(row, col))] = false
        if (col in 1 until c && grid[row][col-1] < grid[row][col])
            dsjs.count[dsjs.find(node(row, col))] = false
        if (col in 0 until c-1 && grid[row][col+1] < grid[row][col])
            dsjs.count[dsjs.find(node(row, col))] = false
    }
    cells.map{ (_, row, col) ->
        if (node(row, col).let{ it == dsjs.find(it) && dsjs.count[it] })
            -dsjs.arr[dsjs.find(node(row,col))]
        else 0
    }.sum().let{ println(it) }
}