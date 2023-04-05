import kotlin.math.abs

fun main() {
    val a = readLine()!!.toInt()
    println(getLastDigit(a))
}

fun getLastDigit(a: Int): Int {
    return abs(a % 10)
}
