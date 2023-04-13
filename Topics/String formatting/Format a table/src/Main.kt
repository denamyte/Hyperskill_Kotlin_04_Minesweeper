const val FMT = "%,12.2f"
const val N = 4

fun main() = List(N) { readln().toDouble() }
    .mapIndexed { i, d -> FMT.format(d) + if (i % 2 == 1) "\n" else "" }
    .joinToString("").let(::print)