package strongdmm.controller.frame

import strongdmm.byond.dmi.IconSprite

data class FrameMesh(
    val itemId: Long,
    val sprite: IconSprite,
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    val colorR: Float,
    val colorG: Float,
    val colorB: Float,
    val colorA: Float,
    val depth: Float
)