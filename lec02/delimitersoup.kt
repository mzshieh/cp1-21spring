fun main() {
    val n = readLine()!!.toInt()
    val s = readLine()!!
    val stack = java.util.Stack<Char>()
    for (pos in 0..n) {
        when {
            pos == n -> println("ok so far")
            s[pos] == ' ' -> {}
            s[pos] in "([{" -> stack.push(s[pos])
            (stack.empty() || "${stack.peek()}${s[pos]}" !in listOf("()", "{}", "[]")) -> {
                    println("${s[pos]} $pos")
                    return
            }
            else -> stack.pop()
        }
    }
}