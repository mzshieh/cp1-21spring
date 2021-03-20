data class Client(val money: Long, val time: Int)

fun main() {
    val (n, t) = readLine()!!.split(" ").map{ it.toInt() }
    val clients = List<Client>(n) {
        val (money, time) = readLine()!!.split(" ").map{ it.toInt() }
        Client(money.toLong(), time)
    }.sortedBy { -it.money }
    val available = java.util.TreeSet<Int>((0 until n).toList())
    var ans = 0L
    for (c in clients) {
        if (available.first() <= c.time) {
            available.remove(available.floor(c.time)!!)
            if (c.time < t) ans += c.money
        } else {
            available.remove(available.last())
        }
    }
    println(ans)
}