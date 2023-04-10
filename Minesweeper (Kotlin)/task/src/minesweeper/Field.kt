package minesweeper

import kotlin.random.Random

const val MINE = 'X'
const val USER_MINE = '*'
const val SAFE_CELL = '.'
const val FIELD_FOOTER = "—│—————————│"
const val FIELD_HEADER = "\n │123456789│\n$FIELD_FOOTER"
val NUMBERS = '1'..'8'

class Point(val x: Int, val y: Int) {
    constructor(list :List<Int>) : this(list[0], list[1])
}

enum class PointState() {
    Mine,
    SafeCell,
    Number,
    Undefined;

    companion object {
        fun getState (char: Char): PointState = when (char) {
            MINE, USER_MINE -> Mine
            SAFE_CELL -> SafeCell
            in NUMBERS -> Number
            else -> Undefined
        }
    }
}

class Field(private val height: Int,
            private val width: Int,
            private val mineCount: Int) {

    private val heightIndices = 0 until height
    private val widthIndices = 0 until width
    private val cells = List(height) { MutableList(width) { SAFE_CELL } }
    private var userCells: List<MutableList<Char>> = emptyList()
    private var foundMines = 0
    private var wrongMines = 0

    init {
        setRandomMines()
        displayMinesCount()
        copyToUserCells()
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
                        cells[y][x] = mines.count { it }.toString()[0]
                }
            }
        }
    }

    private fun copyToUserCells() {
        userCells = cells.map {
            it.map {
                cell -> if (cell == MINE) SAFE_CELL else cell
            }.toMutableList()
        }
    }

    fun print() {
        println(FIELD_HEADER)
        userCells.forEachIndexed { i, row ->
            println("%s|%s|".format(i + 1, row.joinToString(""))) }
        println(FIELD_FOOTER)
    }

    fun finishConditions(): Boolean =
        foundMines == mineCount && wrongMines == 0


    private fun pointState(p: Point, field: List<MutableList<Char>>) =
        PointState.getState(field[p.y][p.x])

    private fun getUserState(p: Point): PointState = pointState(p, userCells)

    private fun getState(p: Point): PointState = pointState(p, cells)

    private fun setField(p: Point, c: Char) {
        userCells[p.y][p.x] = c
    }

    private fun changeMinesCounts(p: Point, v: Int) {
        if (getState(p) == PointState.Mine) {
            foundMines += v
        } else {
            wrongMines += v
        }
    }

    fun placeOrRemoveMine(p: Point): Boolean = when (getUserState(p)) {
        PointState.Mine -> {
            setField(p, SAFE_CELL)
            changeMinesCounts(p, -1)
            true
        }
        PointState.SafeCell -> {
            setField(p, USER_MINE)
            changeMinesCounts(p, 1)
            true
        }
        PointState.Number, PointState.Undefined -> false
    }
}