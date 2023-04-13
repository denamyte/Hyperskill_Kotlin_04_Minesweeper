// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    println(doubleFormat(672.457, 7, 1))
}

fun doubleFormat(value: Double, width: Int, precision: Int): String {
    return "%${width}.${precision}f".format(value)
}
