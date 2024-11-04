package test_module_1


/*
==5==
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)

    val n = sc.nextInt()
    var sum = 0
    for (i in 1..n) {
        val a = sc.nextInt()
        sum += a

        sum *= 113
        sum %= 10000007
    }
    println(sum)
}
*/


/*
==12==
internal class Point(var x:Int, var y:Int): Movable {
    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}

internal class CloudOfPoints(val points: ArrayList<Point>): Movable {
    override fun move(dx: Int, dy: Int) {
        for (point in points) {
            point.move(dx, dy)
        }
    }
}
 */

/*
==14==
fun String.countVowels(): Int = this.count { it in "aouei" }
 */

/*
==16==
class Circle(val r: Double): Shape() {
    override fun area(): Double = Math.PI * r * r
}

class Rect(val a: Double, val b: Double): Shape() {
    override fun area(): Double = a * b
}

class Square(val a: Double): Shape() {
    override fun area(): Double = a * a
}

fun main() {
    val sc = Scanner(System.`in`)

    val n = sc.nextLine().toInt()
    var sum: Double = 0.0

    for (i in 0..n-1) {
        val row = sc.nextLine().split(" ")
        val type = row[0]
        val shape: Shape = when (type) {
            "R" -> Rect(row[1].toDouble(), row[2].toDouble())
            "C" -> Circle(row[1].toDouble())
            "S" -> Square(row[1].toDouble())
            else -> throw Error("unknown")
        }
        sum += "%.2f".format(shape.area()).replace(',', '.').toDouble()
    }
    println("%.2f".format(sum).replace(',', '.'))
}
 */

fun main() {

}