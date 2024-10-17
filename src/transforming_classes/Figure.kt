package transforming_classes

abstract class Figure(var cx : Int,
                      var cy : Int) : Movable {
     abstract fun area(): Float

     abstract override fun toString(): String

     override fun move(dx: Int, dy: Int) {
          cx += dx
          cy += dy
     }

     fun compareTo(other: Figure) = area().compareTo(other.area())

     fun setX(value: Int) {
          cx = value
     }

     fun setY(value: Int) {
          cy = value
     }

     fun getX(): Int = cx

     fun getY(): Int = cy
}