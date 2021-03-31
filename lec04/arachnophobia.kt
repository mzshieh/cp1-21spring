import java.util.*
import kotlin.collections.ArrayList

class ScannerA {
    val lines = java.io.InputStreamReader(System.`in`).readLines()
    var curLine = 0
    var st = java.util.StringTokenizer(lines[0])
    fun nextInt(): Int {
        while (!st.hasMoreTokens())
            st = java.util.StringTokenizer(lines[++curLine])
        return st.nextToken().toInt()
    }
}

data class EdgeA(val u: Int, val v: Int, val c: Long)
data class CostNode(val cost: Long, val v: Int): Comparable<CostNode> {
    override fun compareTo(other :CostNode) = cost.compareTo(other.cost)
}

fun main(){
    val sc = ScannerA()
    val n = sc.nextInt()
    val m = sc.nextInt()
    val t = sc.nextInt().toLong()
    val adj = List<ArrayList<EdgeA>>(n) { ArrayList() }
    for (round in 1..m) {
        val u = sc.nextInt()
        val v = sc.nextInt()
        val cost = sc.nextInt().toLong()
        adj[u].add(EdgeA(u,v,cost))
        adj[v].add(EdgeA(v,u,cost))
    }
    val src = sc.nextInt()
    val dst = sc.nextInt()
    val pq = PriorityQueue<CostNode>().apply{
        val k = sc.nextInt()
        for (round in 1..k) add(CostNode(0L, sc.nextInt()))
    }
    val spiderDist = MutableList<Long>(n) { 0x3FFFFFFFFFFFFFFFL }
    while (pq.isNotEmpty()) {
        val (cost, v) = pq.poll()
        if (cost >= spiderDist[v]) continue
        spiderDist[v] = cost
        for ((_,u,edgeCost) in adj[v]) {
            if (cost+edgeCost < spiderDist[u])
                pq.add(CostNode(cost+edgeCost, u))
        }
    }
    fun test(value: Long): Boolean {
        if (spiderDist[src] < value) return false
        val minDist = MutableList<Long>(n) { 0x3FFFFFFFFFFFFFFFL }
        pq.add(CostNode(0L,src))
        while (pq.isNotEmpty()) {
            val (cost, v) = pq.poll()
            if (cost >= minDist[v]) continue
            minDist[v] = cost
            for ((_,u,edgeCost) in adj[v]) {
                if (spiderDist[u] >= value && cost+edgeCost < minDist[u])
                    pq.add(CostNode(cost+edgeCost, u))
            }
        }
        return minDist[dst] <= t
    }
    var ans = 0L
    var step = 1L.shl(29)
    while (step > 0L) {
        if (test(ans+step)) ans += step
        step = step.ushr(1)
    }
    println(ans)
}