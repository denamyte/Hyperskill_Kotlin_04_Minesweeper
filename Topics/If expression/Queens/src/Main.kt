import java.util.Scanner
import kotlin.math.abs

fun main() {
    val scanner = Scanner(System.`in`)
    val (x1, y1, x2, y2) = List(4) { scanner.nextInt() }
    val take = x1 == x2 || y1 == y2 || abs(x1 - x2) == abs(y1 - y2)
    println(if (take) "YES" else "NO")
}
