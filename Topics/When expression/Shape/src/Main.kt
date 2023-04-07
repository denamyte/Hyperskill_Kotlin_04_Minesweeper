fun main(args: Array<String>) {
    val shapes = mapOf(
        "1" to "square",
        "2" to "circle",
        "3" to "triangle",
        "4" to "rhombus"
    )
    val choice = readln()
    println(
        if (choice in shapes) "You have chosen a ${shapes[choice]}"
        else "There is no such shape!"
    )
}