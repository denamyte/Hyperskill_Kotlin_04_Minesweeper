import java.util.*
import java.util.stream.IntStream

fun main() = IntStream.generate(Scanner(System.`in`)::nextInt)
    .takeWhile { it > 0 }.sum().let(::print)