fun main() {
    val word = readln()
    ('a'..'z').filterNot { it in word }.forEach { print(it) }
}