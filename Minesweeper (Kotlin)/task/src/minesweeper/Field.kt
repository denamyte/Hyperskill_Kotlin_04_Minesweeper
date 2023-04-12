package minesweeper

import kotlin.random.Random

const val MINE = 'X'
const val USER_MINE = '*'
const val HIDDEN_CELL = '.'
const val OPEN_CELL = '/'
const val FIELD_FOOTER = "—│—————————│"
const val FIELD_HEADER = "\n │123456789│\n$FIELD_FOOTER"
val NUMBERS = '1'..'8'
val SEARCH_RANGE = -1..1

class Point(val x: Int, val y: Int) {
    constructor(list :List<Int>) : this(list[0], list[1])
}

enum class PointType {
    Mine,
    OpenCell,
    Number,
    Undefined;

    companion object {
        fun getType (char: Char): PointType = when (char) {
            MINE -> Mine
            OPEN_CELL -> OpenCell
            in NUMBERS -> Number
            else -> Undefined
        }
    }
}

data class Mark(val point: Point, val action: String) {
    constructor(raw: List<String>): this(
        Point(raw.take(2).map(String::toInt).map { it - 1 }),
        raw.last())
}

class Counters(/** Total amount of mines on the field */
               private val mines: Int,
               /** Maximum of non-mine open cells needed to win the game */
               private val cells: Int) {
    /** Correctly marked mines */
    private var foundMines = 0
    /** Non-mine open cells */
    private var openCells = 0

    fun finishConditions(): Boolean = foundMines == mines || openCells == cells

    fun openCell() {
        openCells += 1
    }

    fun findMine(increase: Boolean = true) {
        foundMines += if (increase) 1 else -1
    }
}

class Field(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int
) {
    private val cells = List(height) { MutableList(width) { HIDDEN_CELL } }
    private val visibleCells = List(height) { MutableList(width) { HIDDEN_CELL } }

    private val vertRange = 0 until height
    private val horzRange = 0 until width

    private val counters = Counters(mineCount, height * width - mineCount)

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

    /** Fill the hidden field with all types of marks */
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

    fun finishConditions(): Boolean = counters.finishConditions()

    fun print() {
        println(FIELD_HEADER)
        visibleCells.forEachIndexed { i, row ->
            println("%s|%s|".format(i + 1, row.joinToString(""))) }
        println(FIELD_FOOTER)
    }

    /**
     * Mark the cell according to mark action
     * @return false when trying to free a mine cell, true - otherwise
     */
    fun markCell(mark: Mark): Boolean {
        val p = mark.point
        val type = PointType.getType(cells[p.y][p.x])
        when (mark.action) {
            "free" -> when (type) {
                PointType.Mine -> {
                    openAllMines()
                    return false
                }
                PointType.Number, PointType.OpenCell -> markFreeCell(p.x, p.y)
            }
            "mine" -> markMine(p.x, p.y)
        }
        return true
    }

    private fun openAllMines() =
        cells.forEachIndexed { y, rows ->
            rows.forEachIndexed { x, cell ->
                if (cell == MINE) visibleCells[y][x] = MINE
            }
        }

    private fun markFreeCell(x: Int, y: Int) {
        if (x !in horzRange ||
            y !in vertRange) return

        val vCell = visibleCells[y][x]

        if (vCell != USER_MINE && vCell != HIDDEN_CELL) return

        if (vCell == USER_MINE) counters.findMine()

        counters.openCell()
        visibleCells[y][x] = cells[y][x]

        // There is nothing to search if the current cell is a number cell
        if (cells[y][x] in NUMBERS) return

        // There is only '/' option remaining for the current cell
        // Recursive calls in the 3x3 area around the point(x, y) to mark empty and numbered cells
        for (ySearch in SEARCH_RANGE) {
            for (xSearch in SEARCH_RANGE) {
                markFreeCell(x + xSearch, y + ySearch)
            }
        }
    }

    private fun markMine(x: Int, y: Int) {
        if (visibleCells[y][x] == USER_MINE) {
            counters.findMine(cells[y][x] != MINE)
            visibleCells[y][x] = HIDDEN_CELL
        } else {
            counters.findMine(cells[y][x] == MINE)
            visibleCells[y][x] = USER_MINE
        }
    }

}