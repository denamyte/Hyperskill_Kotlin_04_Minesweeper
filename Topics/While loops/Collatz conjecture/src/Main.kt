fun main() {
    val seq = mutableListOf(readln().toInt())
    while (seq.last() != 1) {
        seq.add(
            if (seq.last() % 2 == 0) seq.last() / 2
            else seq.last() * 3 + 1
        )
    }
    println(seq.joinToString(" "))
}