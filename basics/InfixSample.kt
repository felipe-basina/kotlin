fun main() {

    // Used to add operations to base 'Int' class (object)
    infix fun Int.times(str: String) = str.repeat(this);
    println(2 times "Bye");
    println(2.times("Tschüss"));

    val sophia = Person("Sophia");
    val claudia = Person("Claudia");

    sophia likes claudia;

    println("Sophia's liked people list is null or empty? " + sophia.likedPeople.isNullOrEmpty());
    println("Claudia's liked people list is null or empty? " + claudia.likedPeople.isNullOrEmpty());

    println("Sophia's liked people list:");
    for (person in sophia.likedPeople) {
        println(person.name);
    }

}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>();
    infix fun likes(other: Person) { likedPeople.add(other); }
}
