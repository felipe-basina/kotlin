fun main() {

    // Used to add operations to base 'Int' class (object)
    infix fun Int.times(str: String) = str.repeat(this);
    println(2 times "Bye");
    println(2.times("Tschüss");

}