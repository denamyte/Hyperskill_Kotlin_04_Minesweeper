import kotlin.math.hypot

fun perimeter(vararg crd: Double): Double {
    val s = crd.size
    return (0..s - 2 step 2)
        .sumOf { hypot(crd[it] - crd[(it + 2) % s], crd[it + 1] - crd[(it + 3) % s]) }
}
