fun main() {
    var ans = 0
    val n = readLine()!!.toInt()
    val h = readLine()!!.split(" ").map{ it.toInt() }
    val stack = ArrayList<Pair<Int,Int>>()
    for (i in 0 until n) {
        var bottom = h[i]
        while (stack.size > 0) {
            val last = stack.removeAt(stack.size-1)
            bottom = minOf(last.second, bottom)
            if (last.first > h[i]) {
                stack.add(Pair(last.first, bottom))
                ans = maxOf(ans, h[i]-bottom)
                break
            }
            ans = maxOf(ans, last.first - bottom)
        }
        stack.add(Pair(h[i],h[i]))
    }
    println(ans)
}