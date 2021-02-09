fun main() {
    val colors = listOf("Red", "Green", "Blue")
    println(colors)

    val filteredColors = colors.filter { color -> color.toLowerCase().contains("r") }
    println(filteredColors)
}