package class_robot

class Robot(private var x: Int, private var y: Int, private var direction: Direction) {
    fun getDirection(): Direction {
        return direction
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun turnLeft() {
        direction = when(direction) {
            Direction.UP -> Direction.LEFT
            Direction.LEFT -> Direction.DOWN
            Direction.DOWN -> Direction.RIGHT
            Direction.RIGHT -> Direction.UP
        }
    }

    fun turnRight() {
        direction = when(direction) {
            Direction.UP -> Direction.RIGHT
            Direction.LEFT -> Direction.UP
            Direction.DOWN -> Direction.LEFT
            Direction.RIGHT -> Direction.DOWN
        }
    }

    fun stepForward() {
        when(direction) {
            Direction.UP -> y++
            Direction.LEFT -> x--
            Direction.DOWN -> y--
            Direction.RIGHT -> x++
        }
    }

    override fun toString(): String {
        return "($x, $y), direction: $direction"
    }
}