package transforming_classes

abstract class Figure(var cx : Int,
                      var cy : Int) : Movable, Transforming {
     fun setX(value: Int) {
          cx = value
     }

     fun setY(value: Int) {
          cy = value
     }

     fun getX(): Int = cx

     fun getY(): Int = cy

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

     abstract override fun resize(zoom: Int)

     abstract fun area(): Float

     override fun toString(): String = "($cx, $cy), area = ${area()}"

     override fun move(dx: Int, dy: Int) {
          cx += dx
          cy += dy
     }

     fun compareTo(other: Figure) = area().compareTo(other.area())
}