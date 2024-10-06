import java.util.*


fun main() {
    data class Message(val address: String?,
                       val topic: String?,
                       val to: String?,
                       val text: String?,
    ) {
        fun toHTML(): String {
            var template = ""
            address?.let { template += "<tr><td>adress:</td><td>$it</td></tr>" }
            topic?.let { template += "<tr><td>topic:</td><td>$it</td></tr>" }
            to?.let { template += "<tr><td>to:</td><td>$it</td></tr>" }
            text?.let { template += "<tr><td>text:</td><td>$it</td></tr>" }

            return template
        }

//        fun write(path: String) {
//
//        }
    }

    val m = Message("askbill@microsoft.com", null, "glebbyslave@bk.ru", "Back to your cave!!")
    println(m.toHTML())
}