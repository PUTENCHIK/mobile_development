package is_valid_cc_number

fun String.isValidCCNumber(): Boolean {
    var array: IntArray = intArrayOf()
    var i: Int = 1
    val inverse = this.reversed()

    inverse.map {
        if (it.isDigit()) {
            val x = it.digitToInt()
            val plus = (if (i%2 == 0) (if (x*2 > 9) (x*2 - 9) else x*2) else x)
            array += plus
            i++
        }
    }

    return array.sum() % 10 == 0
}