data class Node(var c: Char? = null, var prev: Node? = null, var next: Node? = null)

fun main() {
    val nCases = readLine()!!.toInt()
    for (case in 1..nCases) {
        val head = Node()
        val tail = Node(null, head, null)
        head.next = tail
        var cur = head
        for (c in readLine()!!) {
            when (c) {
                '[' -> {
                    cur = head
                }
                ']' -> {
                    cur = tail.prev!!
                }
                '<' -> {
                    if (cur != head) {
                        val p = cur.prev
                        val n = cur.next
                        if (p != null) p.next = n
                        if (n != null) n.prev = p
                        cur = p!!
                    }
                }
                else -> {
                    val newNode = Node(c, cur, cur.next)
                    newNode.next!!.prev = newNode
                    newNode.prev!!.next = newNode
                    cur = newNode
                }
            }
        }
        val sb = StringBuilder()
        cur = head.next!!
        do {
            sb.append(cur.c)
            cur = cur.next!!
        } while (cur != tail)
        println(sb)
    }
}
