package minesweeper

const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9
const val MINES_PROMPT = "How many mines do you want on the field? "
const val CRD_PROMPT = "Set/delete mines marks (x and y coordinates): "
const val NUMBER_WARNING = "There is a number here!"
const val CONGRATS = "Congratulations! You found all the mines!"

class Menu() {
    private lateinit var field: Field

    fun run() {
        field = Field(
            FIELD_HEIGHT,
            FIELD_WIDTH,
            inputMinesCount()
        )

        while (!field.finishConditions()) {
            field.print()

            while (!field.placeOrRemoveMine(inputCoordinates()))
                println(NUMBER_WARNING)
        }

        field.print()
        println(CONGRATS)
    }

    private fun inputMinesCount(): Int {
        print(MINES_PROMPT)
        return readln().toInt()
    }

    private fun inputCoordinates(): Point {
        print(CRD_PROMPT)
        return Point(readln().split(" ").map(String::toInt).map { it - 1 })
    }

}