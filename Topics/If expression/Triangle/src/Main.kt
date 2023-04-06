fun main() {
    val list = List(3) { readln().toFloat() }
    val half = list.sum() / 2.0
    println(if (list.all { it < half }) "YES" else "NO")
}