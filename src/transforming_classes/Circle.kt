package transforming_classes

// TODO: дополнить определение класса размерами и позицией
class Circle : Figure(0, 0) {
    // TODO: реализовать интерфейс Transforming
    override fun area(): Float {
        return 0.toFloat();
    }

    override fun toString(): String {
        return "($cx, $cy) r = "
    }
}