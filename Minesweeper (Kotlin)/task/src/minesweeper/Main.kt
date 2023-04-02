package minesweeper

const val MINE = "X"
const val SAFE_CELL = "."
const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9

fun main() {
    val field = List(FIELD_HEIGHT) { MutableList(FIELD_WIDTH) { SAFE_CELL } }
    setMine(field, 1, 1)
    printField(field)
}

fun setMine(field: List<MutableList<String>>, y: Int, x: Int) {
    field[y][x] = MINE
}

fun printField(field: List<List<String>>) {
    field.forEach { println(it.joinToString("")) }
}
