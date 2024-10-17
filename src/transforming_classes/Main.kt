package transforming_classes

fun main() {
    val figures = mapOf<String, Figure>(
        "circle" to Circle(0, 1, 4),
        "rect" to Rect(3, 2, 5, 3),
        "square" to Square(5, 3, 2),
    )

    for (key in figures.keys) {
        val figure = figures[key]
        println("start $key: $figure")
        figure?.resize(2)
        println("resized: $figure")
        figure?.move(-2, -2)
        println("moved: $figure")
        figure?.rotate(RotateDirection.Clockwise, 0, 0)
        println("rotated: $figure")

        println()
    }
}