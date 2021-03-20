data class Cell(val cost: Int, val r: Int, val c: Int)

private class DSJS(val size: Int) {
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
    val (r, c) = readLine()!!.split(" ").map{ it.toInt() }
    val cells = List<List<Cell>>(r){ row ->
        readLine()!!.split(" ").mapIndexed{ col, v ->
            Cell(v.toInt(), row, col) }
    }.flatten().sortedBy{ it.cost }
    val dsjs = DSJS(r*c+2)
    val node = {x: Int, y: Int ->
        x*c + y
    }
    val visited = List<BooleanArray>(r){BooleanArray(c)}
    for ((cost, row, col) in cells) {
        visited[row][col] = true
        if (row in 1 until r && visited[row-1][col])
            dsjs.union(node(row, col), node(row-1, col))
        if (row in 0 until r-1 && visited[row+1][col])
            dsjs.union(node(row, col), node(row+1, col))
        if (col in 1 until c && visited[row][col-1])
            dsjs.union(node(row, col), node(row, col-1))
        if (col in 0 until c-1 && visited[row][col+1])
            dsjs.union(node(row, col), node(row, col+1))
        if (col == 0) dsjs.union(node(row,col),r*c)
        if (col == c-1) dsjs.union(node(row,col),1+r*c)
        if (dsjs.find(r*c) == dsjs.find(1+r*c)) {
            println(cost)
            break
        }
    }
}