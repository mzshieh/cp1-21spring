class ScannerB {
    val lines = java.io.InputStreamReader(System.`in`).readLines()
    var curLine = 0
    var st = java.util.StringTokenizer(lines[0])
    fun next(): String {
        while (!st.hasMoreTokens())
            st = java.util.StringTokenizer(lines[++curLine])
        return st.nextToken()
    }
    fun nextInt() = next().toInt()
}

class FenwickF(size: Int) {
    val data = LongArray(size+1)
    fun update(pos: Int, diff: Long) {
        var p = pos + 1
        while (p < data.size) {
            data[p] += diff
            p += p and -p
        }
    }
    fun query(pos: Int): Long {
        var ret = 0L
        var p = pos
        while (p > 0) {
            ret += data[p]
            p -= p and -p
        }
        return ret
    }
}

fun main(){
    val sc = ScannerB()
    val n = sc.nextInt()
    val q = sc.nextInt()
    val fenwick = FenwickF(n)
    val sb = StringBuilder()
    for (round in 1..q) {
        val op = sc.next()
        if (op == "+") {
            val pos = sc.nextInt()
            val diff = sc.nextInt().toLong()
            fenwick.update(pos, diff)
        } else {
            sb.append("${fenwick.query(sc.nextInt())}\n")
        }
    }
    println(sb)
}