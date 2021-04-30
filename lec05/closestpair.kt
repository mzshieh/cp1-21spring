class Scanner{
    val lines = java.io.InputStreamReader(System.`in`).readLines()
    var cur = 0
    var st = java.util.StringTokenizer(lines[0])
    fun next(): String {
        while(!st.hasMoreTokens())
            st = java.util.StringTokenizer(lines[++cur])
        return st.nextToken()
    }
    fun nextInt() = next().toInt()
    fun nextDouble() = next().toDouble()
}

data class Point(val x: Double, val y: Double): Comparable<Point> {
    infix fun dist(other: Point): Double {
        val dx = x - other.x
        val dy = y - other.y
        return (dx*dx + dy*dy).let{ kotlin.math.sqrt(it) }
    }

    override fun compareTo(other: Point): Int {
        if (x != other.x) return x.compareTo(other.x)
        return y.compareTo(y)
    }
}

fun closestPair(points: MutableList<Point>): Pair<Point, Point> {
    if (points.size <= 3) {
        points.sortBy{ it.y }
        var best = Double.POSITIVE_INFINITY
        var bestI = 0
        var bestJ = 1
        for (i in 0 until points.size) {
            for (j in i+1 until points.size) {
                points[i].dist(points[j]).also{
                    if (it < best) {
                        best = it
                        bestI = i
                        bestJ = j
                    }
                }
            }
        }
        return Pair(points[bestI], points[bestJ])
    }
    val mid = points.size / 2
    val left = ArrayList<Point>(points.subList(0, mid))
    val right = ArrayList<Point>(points.subList(mid, points.size))
    var (p1, p2) = closestPair(left)
    var (q1, q2) = closestPair(right)
    var ans = if (q1 dist q2 < p1 dist p2) Pair(q1, q2) else Pair(p1, p2)
    var best = ans.first dist ans.second
    var l = 0
    var r = 0
    while (l < left.size && r < right.size)
        points[l+r] = if (left[l].y < right[r].y) left[l++] else right[r++]
    while (l < left.size)
        points[l+r] = left[l++]
    while (r < right.size)
        points[l+r] = right[r++]
    for (i in 1 until points.size) {
        for (j in maxOf(0, i-4) until i) {
            (points[i] dist points[j]).also {
                if (best > it) {
                    best = it
                    ans = Pair(points[j], points[i])
                }
            }
        }
    }
    // divide and conquer phase
    return ans
}

fun main() {
    val sc = Scanner()
    var n = sc.nextInt()
    val buf = StringBuilder()
    while (n != 0) {
        val points = MutableList<Point>(n){ sc.nextDouble().let{ Point(it, sc.nextDouble())} }.apply{ sort() }
        val (p1, p2) = closestPair(points)
        val (x1, y1) = p1
        val (x2, y2) = p2
        buf.append("%.2f %.2f %.2f %.2f\n".format(x1, y1, x2, y2))
        n = sc.nextInt()
    }
    print(buf)
}
