fun eval(ops: String): Int {
    if (ops.length == 0) return 4
    if ('+' in ops || '-' in ops) {
        val lastIndex =ops.indexOfLast{ it == '+' || it == '-' }
        return if (ops[lastIndex] == '+')
            eval(ops.substring(0,lastIndex)) + eval(ops.substring(lastIndex+1))
        else eval(ops.substring(0,lastIndex)) - eval(ops.substring(lastIndex+1))
    }
    return if (ops.last() == '*') eval(ops.dropLast(1)) * 4 else eval(ops.dropLast(1)) / 4
}

fun main() {
    val ans = HashMap<Int, String>()
    for (a in "+-*/")
        for (b in "+-*/")
            for (c in "+-*/") {
                val cand = "$a$b$c"
                ans[eval(cand)] = "4 $a 4 $b 4 $c 4"
            }
    val numCases = readLine()!!.toInt()
    for (round in 1..numCases) {
        val n = readLine()!!.toInt()
        if (ans.contains(n)) {
            println("${ans[n]} = $n")
        } else {
            println("no solution")
        }
    }
}