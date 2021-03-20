import java.util.*

fun main() {
    val nCases = readLine()!!.toInt()
    for (cas in 1..nCases) {
        val n = readLine()!!.toInt()
        val pq = PriorityQueue<Long>()
        var ans = 0L
        readLine()!!.split(" ").map{ it.toLong() }.forEach{ pq.add(it) }
        while (pq.size > 1) {
            val cost = pq.remove() + pq.remove()
            ans += cost
            pq.add(cost)
        }
        println(ans)
    }
}