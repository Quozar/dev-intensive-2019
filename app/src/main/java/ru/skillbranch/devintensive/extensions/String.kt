package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16) : String {
    var string = this
    string = string.trim()
    if (string.length < length)
        return string
    string = string.substring(0, length)
    return if (string.endsWith(" "))
        string.substring(0, length - 1) + "..."
    else
        string.substring(0, length) + "..."
}
/*          TO  DO  THIS!!!
fun String.stripHtml() : String {
    var string: String = ""
    for (i in 0 until this.length - 1) {
        if (this[i] == '<') {

        }
    }
    //return string.toString()

}*/