import kotlin.random.Random

fun createDiceGameRandomizer(n: Int): Int {
    var seed = 0
    do {
        val r = Random(++seed)
        val res = List(2) { List(n) { r.nextInt(1, 7) }.sum() }
    } while (res.first() >= res.last())
    return seed
}
