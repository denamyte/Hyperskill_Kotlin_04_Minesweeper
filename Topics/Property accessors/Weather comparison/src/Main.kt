val DEGREES_RANGE = -92..57
val CITIES_MAP = mapOf(Pair("Dubai", 30), Pair("Moscow", 5), Pair("Hanoi", 20))

class City(val name: String, private val defaultDegrees: Int, degrees: Int) {
    val degrees: Int = if (degrees in DEGREES_RANGE) degrees else defaultDegrees
}

fun main() {
    val cities = CITIES_MAP.map { City(it.key, it.value, readln().toInt()) }
        .sortedBy { city -> city.degrees }
    println(if (cities[0].degrees == cities[1].degrees) "neither" else cities[0].name)
}