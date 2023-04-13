import java.util.Scanner

fun main() {
    Scanner(System.`in`)
        .tokens()
        .map(String::toInt)
        .takeWhile { it != 0 }
        .max(Comparator.comparingInt { it })
        .get().let { println(it) }
}