import java.util.TreeMap
import java.util.TreeSet

fun main() {
    var n = readLine()!!.toInt()
    while (n != 0) {
        val itemToNames = TreeMap<String,TreeSet<String>>()
        for (rnd in 1..n) {
            val toks = readLine()!!.split(" ").filter{ it!="" }
            val name = toks[0]
            toks.drop(1).forEach {
                itemToNames.getOrPut(it){TreeSet<String>()}.add(name)
            }
        }
        for ((k, v) in itemToNames) {
            println("$k ${v.joinToString(" ")}")
        }
        println("")
        n = readLine()!!.toInt()
    }
}