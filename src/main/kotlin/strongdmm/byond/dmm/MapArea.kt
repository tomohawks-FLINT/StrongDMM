package strongdmm.byond.dmm

import strongdmm.util.OUT_OF_BOUNDS

data class MapArea(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) {
    fun isInBounds(x: Int, y: Int): Boolean = x in x1..x2 && y in y1..y2

    fun isNotOutOfBounds(): Boolean {
        return x1 != OUT_OF_BOUNDS && y1 != OUT_OF_BOUNDS && x2 != OUT_OF_BOUNDS && y2 != OUT_OF_BOUNDS
    }
}
