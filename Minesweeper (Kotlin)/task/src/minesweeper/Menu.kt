package minesweeper

const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9
const val MINES_PROMPT = "How many mines do you want on the field? "
const val CRD_PROMPT = "Set/unset mines marks or claim a cell as free: "
const val CONGRATS = "Congratulations! You found all the mines!"
const val FAIL = "You stepped on a mine and failed!"

enum class GameState {
    PROGRESS,
    WIN,
    LOOSE,
}

fun runMenu() {
    val field = Field(
        FIELD_HEIGHT,
        FIELD_WIDTH,
        inputMinesCount()
    )
    var state = GameState.PROGRESS

    while (state == GameState.PROGRESS) {
        field.print()

        if (!field.markCell(inputCoordinates())) {
            state = GameState.LOOSE
        } else if (field.finishConditions()) {
            state = GameState.WIN
        }
    }

    field.print()
    println(if (state == GameState.WIN) CONGRATS else FAIL)
}

private fun inputMinesCount(): Int {
    print(MINES_PROMPT)
    return readln().toInt()
}

private fun inputCoordinates(): Mark {
    print(CRD_PROMPT)
    return Mark(readln().split(" "))
}
