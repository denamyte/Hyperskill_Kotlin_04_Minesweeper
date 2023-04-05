fun divide(a: Long, b: Long): Double {
    return a.toDouble() / b
}

/* Do not change code below */
fun main() {
    val a = readln().toLong()
    val b = readln().toLong()
    println(divide(a, b))
}
