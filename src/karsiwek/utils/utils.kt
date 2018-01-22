package karsiwek.utils

operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>) : Pair<Int,Int> {
    return Pair(this.first + other.first, this.second + other.second);
}
fun <E> List<E>.tail() : List<E> = this.subList(1, this.size)

fun buildString(build: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.build()
    return stringBuilder.toString()
}

val s = buildString {
    this.append("Numbers: ")
    for (i in 1..3) {
        // 'this' can be omitted
        append(i)
    }
}
