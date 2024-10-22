package transforming_classes
import kotlin.math.abs

// сочетание определения класса и конструктора одновременно объявляет переменные и задаёт их значения
class Rect(cx: Int,
           cy: Int,
           private var width: Int,
           private var height: Int) : Movable, Figure(cx, cy) {

    fun setWidth(value: Int) {
        width = value
    }

    fun setHeight(value: Int) {
        height = value
    }

    fun getWidth(): Int = width

    fun getHeight(): Int = height

    override fun resize(zoom: Int) {
        val z = abs(zoom)
        if (z == 0) {
            throw Error("Zoom value is zero")
        }
        width *= z
        height *= z
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        super.rotate(direction, centerX, centerY)
        val buffer = width
        width = height
        height = buffer
    }

    override fun area(): Float = (width*height).toFloat()

    override fun toString(): String {
        return super.toString() + ", w = $width, h = $height"
    }
}