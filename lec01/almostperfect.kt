fun main() {
    var n = readLine()?.toInt()
    while (n != null) {
        var s = 0L
        loop@for (i in 1..n) {
            if (n % i != 0) continue
            when (i * i) {
                in 1 until n -> s += i + n / i
                n -> s += i
                else -> break@loop
            }
        }
        when (s) {
            2L * n -> println("$n perfect")
            in 2L * (n-1) .. 2L * (n+1) -> println("$n almost perfect")
            else -> println("$n not perfect")
        }
        n = readLine()?.toInt()
    }
}