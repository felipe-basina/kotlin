fun printMessage(message: String) : Unit {
    println(message);
}

fun printMessageWithPrefix(message: String, prefix: String = "INFO") {
    println("[$prefix] $message");
}

fun sumInt(x: Int, y: Int) : Int {
    return x + y;
}

fun sumIntInferred(x: Int, y: Int) = x + y;

fun main() {
    printMessage("First message");
    printMessageWithPrefix("Another message without prefix");
    printMessageWithPrefix("Another message with prefix", "DEBUG");
    printMessageWithPrefix(prefix = "WARN", message = "A message....");
    println(sumInt(10, 6));
    println(sumIntInferred(10, 6));
}