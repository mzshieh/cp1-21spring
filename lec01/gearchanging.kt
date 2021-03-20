fun main() {
    val (n, m, p) = readLine()!!.split(" ").map{ it.toInt() }
    val c = readLine()!!.split(" ").map{ it.toDouble() }
    val d = readLine()!!.split(" ").map{ it.toDouble() }
    val r = 1 + p.toDouble()/100
    val cadence = c.map{ v -> d.map{ v/it } }.flatten().sorted()
    (1 until n*m).all{ cadence[it] <= r * cadence[it-1] }.let{
        println( if (it) "Ride on!" else "Time to change gears!" )
    }
}