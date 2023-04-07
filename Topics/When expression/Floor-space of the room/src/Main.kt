import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    when (readln()) {
        "triangle" -> {
            val sides = Array(3) { readln().toDouble() }
            val s = sides.sum() / 2
            sqrt((s * sides.map { s - it }.reduce { acc, side -> acc * side }))
        }
        "rectangle" -> Array(2) { readln().toDouble() }.reduce { acc, d -> acc * d }
        "circle" -> 3.14 * readln().toDouble().pow(2)
        else -> 1
    }.let { println(it) }
}