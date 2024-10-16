package transforming_classes
import kotlin.math.abs
import kotlin.math.pow

class Square(private var x: Int,
             private var y: Int,
             private var a: Int) : Transforming, Movable, Figure(0) {
    fun setX(value: Int) {
        x = value
    }

    fun setY(value: Int) {
        y = value
    }

    fun setA(value: Int) {
        a = value
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
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
        val newX = centerX + abs(y - centerY) * when (direction) {
            RotateDirection.Clockwise -> 1
            RotateDirection.CounterClockwise -> -1
        }
        val newY = centerY + abs(x - centerX) * when (direction) {
            RotateDirection.Clockwise -> -1
            RotateDirection.CounterClockwise -> 1
        }

        x = newX
        y = newY
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }

    override fun toString(): String {
        return "($x, $y), side = $a"
    }
}