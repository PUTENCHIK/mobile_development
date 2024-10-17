package transforming_classes
import kotlin.math.abs

class Circle(cx: Int,
             cy: Int,
             private var radius: Int) : Transforming, Figure(cx, cy) {
    fun setRadius(value: Int) {
        radius = value
    }

    fun getRadius(): Int = radius

    override fun area(): Float = (radius*radius*Math.PI).toFloat()

    override fun resize(zoom: Int) {
        val z = abs(zoom)
        if (z == 0) {
            throw Error("Zoom value is zero")
        }
        radius *= z
    }

    override fun toString(): String {
        return super.toString() + ", radius = $radius"
    }
}