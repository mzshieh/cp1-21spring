import java.util.ArrayDeque

fun main() {
    val (n, m) = readLine()!!.split(" ").map{ it.toInt() }
    val adj = List<ArrayList<Int>>(2*n){ ArrayList<Int>() }
    val deg = IntArray(n)
    for (round in 1..m) {
        val (a, v) = readLine()!!.split(" ").map { it.toInt() }
        for (x in 0 until n)
            adj[n + x].add(x)
        if (a < 0) {
            val u = -a
            adj[u - 1].add(v - 1)
            adj[n + u - 1].add(n + v - 1)
            deg[u - 1]++
        } else {
            adj[n + a - 1].add(v - 1)
        }
    }
    val q = ArrayDeque<Int>().apply{ add(n) }
    val enqueued = MutableList<Boolean>(2*n){ it == n }
    while (!q.isEmpty()) {
        val u = q.removeFirst()
        for (p in adj[u]) {
            if (!enqueued[p]) {
                q.offerLast(p)
                enqueued[p] = true
            }
        }
    }
    (0 until n).count{ enqueued[it] && deg[it] == 0 }.let{ println(it) }
}