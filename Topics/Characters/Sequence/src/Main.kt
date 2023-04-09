const val COUNT = 3

fun main() = List(COUNT) { readln()[0] }
    .zipWithNext { a, b -> b - a }.toSet()
    .let { println(it.size == 1 && it.first() == 1) }
