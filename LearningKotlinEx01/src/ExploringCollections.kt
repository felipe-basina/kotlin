fun main() {
    val colors = listOf("Red", "Green", "Blue")
    println(colors)

    val filteredColors = colors.filter { color -> color.toLowerCase().contains("r") }
    println(filteredColors)

    val days = mutableListOf("Monday", "Tuesday", "Wednesday")
    println(days)

    val months = mutableSetOf("Jan", "Feb")
    println(months)
    val moreMonths = months.add("Mar")
    println("months: $months, moreMonths? $moreMonths")

    var notMutableMonthsSet = setOf("Jun", "Jul")
    notMutableMonthsSet = notMutableMonthsSet.plus("Ago")
    println(notMutableMonthsSet)

    val webColors = mapOf("red" to "ff0000", "blue" to "00ff00")
    println(webColors)
    println(webColors["blue"])

    //val intArray: Array<Int> = arrayOf(1,2,3,4,5) same as below
    val intArray: IntArray = intArrayOf(1,2,3,4,5)
    intArray[3] = -4
    intArray.forEach { println(it) } // reserved key word, could be replaced by intVal -> println(val)
    intArray.forEach { intVal -> println(intVal) }
}