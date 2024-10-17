package transforming_classes
import kotlin.math.abs

class Square(cx: Int,
             cy: Int,
             private var a: Int) : Figure(cx, cy) {
    fun setA(value: Int) {
        a = value
    }

    fun getA(): Int {
        return a
    }

    override fun area(): Float {
        return (a*a).toFloat()
    }

    override fun resize(zoom: Int) {
        val z = abs(zoom)
        if (z == 0) {
            throw Error("Zoom value is zero")
        }
        a *= z
    }

    override fun toString(): String {
        return super.toString() + ", side = $a"
    }
}