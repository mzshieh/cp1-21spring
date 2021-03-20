import java.util.ArrayDeque

fun main() {
    val (n, m) = readLine()!!.split(" ").map{ it.toInt() }
    val adj = List(n){ ArrayList<Int>() }
    for (round in 1..m) {
        val (u, v) = readLine()!!.split(" ").map{ it.toInt()-1 }
        adj[u].add(v)
        adj[v].add(u)
    }
    val q = ArrayDeque<Int>().apply{ add(0) }
    val enqueued = MutableList<Boolean>(n){ it == 0 }
    while (!q.isEmpty()) {
        val u = q.removeFirst()
        for (v in adj[u]) {
            if (!enqueued[v]) {
                q.offerLast(v)
                enqueued[v] = true
            }
        }
    }
    if (enqueued.all{ it }) {
        println("Connected")
    } else {
        val buf = StringBuilder()
        for (i in 1 until n)
            if (!enqueued[i])
                buf.append("${i+1}\n")
        print(buf)
    }
}