package minesweeper

import kotlin.random.Random

data class Mark(val point: Point, val action: String) {
    constructor(raw: List<String>): this(
        Point(raw.take(2).map(String::toInt)),
        raw.last())
}

class Field2(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int
) {
    private val cells = List(height) { MutableList(width) { HIDDEN_CELL } }
    private val visibleCells = List(height) { MutableList(width) { HIDDEN_CELL } }

    private val vertRange = 0 until height
    private val horzRange = 0 until width

    /** Correctly marked mines */
    private var foundMines = 0
    /** Incorrectly marked mines */
    private var wrongMarks = 0
    /** Maximum of non-mine open cells needed to win the game */
    private val maxOpenCells = height * width - mineCount
    /** Non-mine open cells */
    private var openCells = 0

    init {
        setRandomMines()
        fillCells()
    }

    private fun setRandomMines() {
        val random = Random.Default
        var remain = mineCount
        while (remain > 0) {
            val y = random.nextInt(height)
            val x = random.nextInt(width)
            if (cells[y][x] == HIDDEN_CELL) {
                remain--
                cells[y][x] = MINE
            }
        }
    }

    /** Fill the field with all types of  */
    private fun fillCells() {
        fun isMine(y: Int, x: Int): Boolean =
            x in horzRange && y in vertRange && cells[y][x] == MINE

        for (y in 0 until height) {
            for (x in 0 until width) {
                if (cells[y][x] == HIDDEN_CELL) {
                    val mines = mutableListOf<Boolean>()
                    for (lookY in y - 1..y + 1)
                        for (lookX in x - 1..x + 1) mines += isMine(lookY, lookX)
                    cells[y][x] =
                        if (mines.contains(true))
                            mines.count { it }.toString()[0]
                        else OPEN_CELL
                }
            }
        }
    }

    fun finishConditions(): Boolean =
        wrongMarks == 0 && (foundMines == mineCount || openCells == maxOpenCells)

    fun print() {
        println(FIELD_HEADER)
        visibleCells.forEachIndexed { i, row ->
            println("%s|%s|".format(i + 1, row.joinToString(""))) }
        println(FIELD_FOOTER)
    }

    private fun getType(p: Point) = PoinType.getType(cells[p.y][p.x])

    /**
     * Mark the cell according to mark action
     * @return false when trying to free a mine cell, true - otherwise
     */
    fun markCell(mark: Mark): Boolean {
        val p = mark.point
        val type = getType(p)
        return when (mark.action) {
            "free" -> {
                if (type == PoinType.Mine) {
                    cells.forEachIndexed { y, rows ->
                        rows.forEachIndexed { x, cell ->
                            if (cell == MINE) visibleCells[y][x] = MINE
                        }
                    }
                    return false
                }

                return true
            }
            "mine" -> {
                true
            }
            else -> false
        }
    }

    fun markFreeCell(x: Int, y: Int) {
        when {
            x in horzRange && y in vertRange -> {
                TODO("Finish this and other cases")
            }
        }
    }

}