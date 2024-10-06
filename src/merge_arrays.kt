import java.util.*


fun mergeArrays(arr1: IntArray, arr2: IntArray): IntArray {
    var i = 0
    var j = 0
    var array: IntArray = intArrayOf()

    while (i < arr1.size || j < arr2.size) {
        if (i == arr1.size) {
            array += arr2[j]
            j++
        } else if (j == arr2.size) {
            array += arr1[i]
            i++
        } else {
            if (arr1[i] <= arr2[j]) {
                array += arr1[i]
                i++
            } else {
                array += arr2[j]
                j++
            }
        }
    }
    return array
}

fun main() {
    val arr1: IntArray = intArrayOf(0, 2, 2)
    val arr2: IntArray = intArrayOf(1)

    val array = mergeArrays(arr1, arr2)
    println("arr1: ${arr1.contentToString()}")
    println("arr2: ${arr2.contentToString()}")
    println("array: ${array.contentToString()}")
}