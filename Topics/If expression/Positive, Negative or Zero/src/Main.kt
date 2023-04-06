fun main() {
    readln().toInt().let {
        println(if (it < 0) "negative" else if (it > 0) "positive" else "zero")
    }
}