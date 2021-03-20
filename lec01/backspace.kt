fun main() {
    val ans = StringBuffer()
    readLine()!!.forEach {
        if (it == '<') {
            ans.deleteCharAt(ans.length - 1)
        } else {
            ans.append(it)
        }
    }
    println(ans.toString())
}