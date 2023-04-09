package minesweeper


fun main() {
    val mineCount = inputInt(MINES_PROMPT)
    val field = Field(FIELD_HEIGHT, FIELD_WIDTH, mineCount)
    field.print()
}

fun inputInt(prompt: String): Int {
    print(prompt)
    return readln().toInt()
}
