import java.util.TreeSet

data class TimeStamp(val t: Int, val isF: Boolean, val id: Int)
data class Act(val f: Int, val id: Int): Comparable<Act> {
    override fun compareTo(other: Act) =
        if (f != other.f) f.compareTo(other.f)
        else id.compareTo(other.id)
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map{ it.toInt() }
    val s = IntArray(n)
    val f = IntArray(n)
    val ts = ArrayList<TimeStamp>()
    for (i in 0 until n) {
        val timestamps = readLine()!!.split(" ").map{ it.toInt() }
        s[i] = timestamps[0]
        f[i] = timestamps[1]
        ts.add(TimeStamp(s[i], false, i))
        ts.add(TimeStamp(f[i], true, i))
    }
    ts.sortBy{ if(it.isF) 1+2*it.t else 2*it.t }
    val depq = TreeSet<Act>()
    var ans = n
    for (timestamp in ts) {
        if (timestamp.isF) {
            if (depq.contains(Act(timestamp.t, timestamp.id))) {
                depq.remove(Act(timestamp.t, timestamp.id))
            }
        } else {
            depq.add(Act(f[timestamp.id], timestamp.id))
            if (depq.size > k) {
                depq.remove(depq.last())
                ans--
            }
        }
    }
    println(ans)
}