package minesweeper

const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9
const val MINES_PROMPT = "How many mines do you want on the field? "
const val CRD_PROMPT = "Set/unset mines marks or claim a cell as free:  "
const val NUMBER_WARNING = "There is a number here!"
const val CONGRATS = "Congratulations! You found all the mines!"

class Menu() {

    fun run() {
        val field = Field(
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

    fun run2() {
        val field = Field2(
            FIELD_HEIGHT,
            FIELD_WIDTH,
            inputMinesCount()
        )

        while (!field.finishConditions()) {
            field.print()

            break
//            while (!field.placeOrRemoveMine(inputCoordinates()))
//                println(NUMBER_WARNING)
        }

    }

    private fun inputMinesCount(): Int {
        print(MINES_PROMPT)
        return readln().toInt()
    }

    private fun inputCoordinates(): Point {
        print(CRD_PROMPT)
        return Point(readln().split(" ").map(String::toInt).map { it - 1 })
    }

    private fun inputCoordinates2(): Mark {
        print(CRD_PROMPT)
        return Mark(readln().split(" "))
    }

}