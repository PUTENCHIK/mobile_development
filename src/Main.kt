import java.util.*


abstract class Figure() {
    abstract fun area(): Float
}


class Circle(val radius: Int = 0): Figure() {
    override fun area(): Float {
        return (radius*radius*Math.PI).toFloat()
    }
}

fun main() {
    val strings = arrayOf("foo", "bar", null, "", 123)
    for (s in strings){
        s?.let { print ("$it ") }
    }
}