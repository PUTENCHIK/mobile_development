package transforming_classes

enum class RotateDirection {
    Clockwise, CounterClockwise
}

interface Transforming {
    // TODO: увеличивает фигуру, не перемещая, с сохранением пропорций
    fun resize(zoom: Int)

    // TODO: поворот фигуры вокруг точки (centerX, centerY) на 90 градусов
    fun rotate(direction: RotateDirection, centerX: Int, centerY: Int)
}
