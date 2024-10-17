package transforming_classes

// сочетание определения класса и конструктора одновременно объявляет переменные и задаёт их значения
class Rect(cx: Int,
           cy: Int,
           val width: Int,
           val height: Int) : Movable, Figure(0, 0) {
    // TODO: реализовать интерфейс Transforming

    // для каждого класса area() определяется по-своему
    override fun area(): Float {
        return (width*height).toFloat() // требуется явное приведение к вещественному числу
    }

    override fun toString(): String {
        return "($cx, $cy), w = $width, h = $height"
    }
}