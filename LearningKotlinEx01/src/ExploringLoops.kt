fun main() {
//    var x: Int = 0
//    while (x < 10) {
//        //println(x++)
//        println(++x)
//    }

    val people = ArrayList<KotlinPerson>()
    people.add(KotlinPerson(1L, "Mr", "James", "Apple", null))
    people.add(KotlinPerson(2L, "Miss", "Sophie", "Orange", null))
    people.add(KotlinPerson(3L, "Mrs", "Anita", "Kumkwat", null))
    people.add(KotlinPerson(4L, "Mr", "Darren", "Banana", null))

    //for (person: KotlinPerson in people) {
    //for (person in people) {
    for ((id, title, firstName) in people) {
        println("$title $firstName has id $id")
    }

    val peopleMap = HashMap<Int, KotlinPerson>()
    peopleMap.put(1, KotlinPerson(1L, "Mr", "James", "Apple", null))
    peopleMap.put(2, KotlinPerson(2L, "Miss", "Sophie", "Orange", null))
    peopleMap.put(3, KotlinPerson(3L, "Mrs", "Anita", "Kumkwat", null))
    peopleMap.put(4, KotlinPerson(4L, "Mr", "Darren", "Banana", null))

    //for (person: KotlinPerson in people) {
    //for (person in people) {
    for ((key, value) in peopleMap) {
        println("$value has key $key")
    }

    for (i in (0..9)) {
        println(i)
    }

    (0..9).forEach{ index -> println(index) }
    (0..9).forEach{ println(it) }
    (9 downTo 0).forEach{ println(it) }
    (0 until 9).forEach{ println(it) }
    (0..9 step 2).forEach{ println(it) }
    ('A'..'F').forEach{ println(it) }
}