package transforming_classes
import kotlin.math.abs

class Square(cx: Int,
             cy: Int,
             private var a: Int) : Transforming, Figure(cx, cy) {
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

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        val newX = centerX + (cy - centerY) * when (direction) {
            RotateDirection.Clockwise -> 1
            RotateDirection.CounterClockwise -> -1
        }
        val newY = centerY + (cx - centerX) * when (direction) {
            RotateDirection.Clockwise -> -1
            RotateDirection.CounterClockwise -> 1
        }

        cx = newX
        cy = newY
    }

    override fun toString(): String {
        return "($cx, $cy), side = $a"
    }
}