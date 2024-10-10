package class_robot

fun moveRobot(robot: Robot, toX: Int, toY: Int) {
    var diff = toX - robot.getX()
    var needDirection = when {
        diff < 0 -> Direction.LEFT
        diff > 0 -> Direction.RIGHT
        else -> null
    }
    while (needDirection != null && robot.getDirection() != needDirection) {
        robot.turnRight()
    }
    while (robot.getX() != toX) {
        robot.stepForward()
    }

    diff = toY - robot.getY()
    needDirection = when {
        diff < 0 -> Direction.DOWN
        diff > 0 -> Direction.UP
        else -> null
    }
    while (needDirection != null && robot.getDirection() != needDirection) {
        robot.turnRight()
    }
    while (robot.getY() != toY) {
        robot.stepForward()
    }
}

fun main() {
    val robot = Robot(5, 6, Direction.UP)
    println("Start state: $robot")
    moveRobot(robot, 3, -5)
    println("Destination state: $robot")
}

























