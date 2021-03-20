fun main() {
    val n = readLine()!!.toInt()
    val v = readLine()!!.split(" ").map{ it == "T" }
    val stack = java.util.Stack<Boolean>()
    readLine()!!.split(" ").map{ it[0] }.forEach {
        when (it) {
            in 'A'..'Z' -> stack.push(v[it-'A'])
            '+' -> stack.push(stack.pop().let{ stack.pop() || it } )
            '*' -> stack.push(stack.pop().let{ stack.pop() && it } )
            else -> stack.push(!stack.pop())
        }
    }
    println(if (stack.pop()) "T" else "F")
}