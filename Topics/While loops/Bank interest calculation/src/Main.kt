const val INTEREST_RATE = 1.071
const val MAX = 700000

fun findYears(depo: Double): Int {
    var deposit = depo
    var years = 0
    do {
        deposit *= INTEREST_RATE
        years++
    } while (deposit <= MAX)
    return years
}
/*
fun main() {
    while (true) {
        val d = readln().toDouble()
        println(findYears(d))
    }
}
*/