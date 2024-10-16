package transforming_classes

fun main() {
    val s = Square(2, 2, 3)
    println(s)

    s.rotate(RotateDirection.CounterClockwise, 1, -1)
    println(s)

    s.rotate(RotateDirection.CounterClockwise, 1, -1)
    println(s)

//    s.rotate(RotateDirection.Clockwise, 1, -1)
//    println(s)

    // интерфейсы удобно использовать в коллекциях
//    val figures: Array<Movable>
//    val movable: Movable = Rect(0,0,1,1)
//    movable.move(1,1)
//    // переменной базового класса
//    val f: Figure = Rect(0,0,1,1)
//    val f2: Figure = Circle()
//
//    println(f.area())
//    println(f2.area())

}