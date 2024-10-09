import java.io.File
import java.lang.Exception


fun main() {
    val tableContent: String = "!!table-content!!"
    val pathTemplate: String = "src/generation_HTML_letter/template.html"
    val pathLetter: String = "src/generation_HTML_letter/letter.html"
    data class Message(val address: String?,
                       val topic: String?,
                       val to: String?,
                       val text: String?,
    ) {
        fun toHTML(): String {
            var template = ""
            address?.let { template += "<tr><td>address:</td><td>$it</td></tr>" }
            topic?.let { template += "<tr><td>topic:</td><td>$it</td></tr>" }
            to?.let { template += "<tr><td>to:</td><td>$it</td></tr>" }
            text?.let { template += "<tr><td>text:</td><td>$it</td></tr>" }

            if (template.isEmpty()) {
                throw Exception("Table hasn't any cells")
            }

            return template
        }

        fun write(text: String) {
            val file = File(pathTemplate)
            if (!file.exists()) {
                throw Exception("File in path $pathTemplate doesn't exist")
            }

            val content = file.readText()
            val modifiedContent = content.replace(tableContent, text)

            val fileLetter = File(pathLetter)
            fileLetter.writeText(modifiedContent)
        }
    }

    val m = Message("megasoft@megasoft.com",
                    "Important message!",
                    "littleglebby@bk.ru",
                    "You're fired!")

    val table = m.toHTML()
    println(table)
    m.write(table)
}