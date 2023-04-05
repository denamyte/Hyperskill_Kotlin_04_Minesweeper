package minesweeper

const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9
const val MINES_PROMPT = "How many mines do you want on the field? "

fun main() {
    val mineCount = inputInt(MINES_PROMPT)
    val field = Field(FIELD_HEIGHT, FIELD_WIDTH, mineCount)
    field.print()
}

fun inputInt(prompt: String): Int {
    print(prompt)
    return readln().toInt()
}
