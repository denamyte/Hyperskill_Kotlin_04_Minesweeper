package minesweeper

import kotlin.random.Random

const val MINE = "X"
const val SAFE_CELL = "."

class Field(private val height: Int,
            private val width: Int,
            private val mineCount: Int) {

    private val cells = List(height) { MutableList(width) { SAFE_CELL } }
    private val heightIndices = 0 until height
    private val widthIndices = 0 until width

    init {
        setRandomMines()
        displayMinesCount()
    }

    private fun setRandomMines() {
        val random = Random.Default
        var remain = mineCount
        while (remain > 0) {
            val y = random.nextInt(height)
            val x = random.nextInt(width)
            if (cells[y][x] == SAFE_CELL) {
                remain--
                cells[y][x] = MINE
            }
        }
    }

    private fun displayMinesCount() {
        fun isMine(y: Int, x: Int): Boolean =
            x in widthIndices && y in heightIndices && cells[y][x] == MINE

        for (y in 0 until height) {
            for (x in 0 until width) {
                if (cells[y][x] == SAFE_CELL) {
                    val mines = mutableListOf<Boolean>()
                    for (lookY in y - 1..y + 1)
                        for (lookX in x - 1..x + 1) mines += isMine(lookY, lookX)
                    if (mines.contains(true))
                        cells[y][x] = mines.count { it }.toString()
                }
            }
        }
    }

    fun print() = cells.forEach { println(it.joinToString("")) }

}