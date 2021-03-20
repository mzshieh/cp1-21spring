fun main() {
    var line = readLine()
    while (line != null) {
        val lower = line.toLowerCase()
        for (i in 0 until lower.length) {
            val suffix = lower.substring(i)
            if (suffix.startsWith("0x")) {
                val j = suffix.substring(2).indexOfFirst{ it !in "0123456789abcdef" } + 2 + i
                if (j >= i + 2) {
                    println("${line.substring(i,j)} ${line.substring(i+2,j).toLong(16)}")
                } else if (suffix.length > 2){
                    println("${line.substring(i)} ${line.substring(i+2).toLong(16)}")
                }
            }
        }
        line = readLine()
    }
}