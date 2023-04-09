package minesweeper

const val FIELD_HEIGHT = 9
const val FIELD_WIDTH = 9
const val MINES_PROMPT = "How many mines do you want on the field? "

class Menu() {
    private lateinit var field: Field

    fun run() {
        field = Field(
            FIELD_HEIGHT,
            FIELD_WIDTH,
            inputInt(MINES_PROMPT)
        )

        while (!field.finishConditions()) {
            TODO("the logic loop should be here")
        }
    }

    private fun inputInt(prompt: String): Int {
        print(prompt)
        return readln().toInt()
    }

}