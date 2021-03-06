package strongdmm.byond.dmm

import strongdmm.byond.*
import strongdmm.byond.dme.DmeItem
import java.util.*

class TileItem(
    val id: Long,
    val dmeItem: DmeItem,
    customVars: Map<String, String>?
) {
    val customVars: Map<String, String>? = customVars?.takeIf { it.isNotEmpty() }?.let { Collections.unmodifiableMap(it) }
    val type: String get() = dmeItem.type

    // Vars extensively used during the rendering process
    val icon: String = getVarText(VAR_ICON) ?: ""
    val iconState: String = getVarText(VAR_ICON_STATE) ?: ""
    val plane: Float = getVarFloat(VAR_PLANE) ?: 0f
    val layer: Float = getVarFloat(VAR_LAYER) ?: 0f
    val pixelX: Int = getVarInt(VAR_PIXEL_X) ?: 0
    val pixelY: Int = getVarInt(VAR_PIXEL_Y) ?: 0
    val stepX: Int = getVarInt(VAR_STEP_X) ?: 0
    val stepY: Int = getVarInt(VAR_STEP_Y) ?: 0
    val dir: Int = getVarInt(VAR_DIR) ?: 0
    val name: String = getVarText(VAR_NAME) ?: ""
    val colorR: Float
    val colorG: Float
    val colorB: Float
    val colorA: Float

    init {
        ColorExtractor.extract(getVarText(VAR_COLOR), getVarInt(VAR_ALPHA) ?: 1).run {
            colorR = r
            colorG = g
            colorB = b
            colorA = a
        }
    }

    fun isType(type: String): Boolean = this.type.startsWith(type)

    fun isSameType(tileItem: TileItem): Boolean {
        return isType(TYPE_AREA) && tileItem.isType(TYPE_AREA) ||
            isType(TYPE_TURF) && tileItem.isType(TYPE_TURF) ||
            isType(TYPE_OBJ) && tileItem.isType(TYPE_OBJ) ||
            isType(TYPE_MOB) && tileItem.isType(TYPE_MOB)
    }

    fun isDefaultInstance(): Boolean = customVars.isNullOrEmpty()

    fun getVarText(name: String): String? {
        return customVars?.get(name)?.takeIf { it.isNotEmpty() }?.run {
            if (length > 1) {
                substring(1, length - 1)
            } else {
                this
            }
        } ?: dmeItem.getVarText(name)
    }

    fun getVarInt(name: String): Int? = customVars?.get(name)?.toIntOrNull() ?: dmeItem.getVarInt(name)

    fun getVarFloat(name: String): Float? = customVars?.get(name)?.toFloatOrNull() ?: dmeItem.getVarFloat(name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TileItem
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
