package minesweeper

import kotlin.random.Random

const val MINE = "X"
const val SAFE_CELL = "."
const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9
const val MINES_PROMPT = "How many mines do you want on the field? "

fun main() {
    val field = List(FIELD_HEIGHT) { MutableList(FIELD_WIDTH) { SAFE_CELL } }
    val mineCount = inputInt(MINES_PROMPT)
    setRandomMines(field, mineCount)
    printField(field)
}

fun inputInt(prompt: String): Int {
    print(prompt)
    return readln().toInt()
}

fun setRandomMines(field: List<MutableList<String>>, mineCount: Int) {
    val random = Random.Default
    var remain = mineCount
    while (remain > 0) {
        val row = random.nextInt(FIELD_HEIGHT)
        val col = random.nextInt(FIELD_WIDTH)
        if (field[row][col] == SAFE_CELL) {
            remain--
            field[row][col] = MINE
        }
    }
}

fun printField(field: List<List<String>>) {
    field.forEach { println(it.joinToString("")) }
}
