import java.util.*

data class KotlinPerson(
    val id: Long,
    val title: String,
    val firstName: String,
    val surname: String,
    val dateOfBirth: Calendar?
) {

    var favoriteColor: String? = null

    fun getLastLetter(string: String) = string.takeLast(1)

    fun getLastLetterOfColor(): String {
        // return if (favoriteColor === null) "" else favoriteColor.takeLast(1) THIS WILL NOT WORK
        // return favoriteColor?.let { this.getLastLetter(it) } ?: "" THIS WILL WORK ('it' when there is only one parameter)
        return favoriteColor?.let { param -> this.getLastLetter(param) } ?: "" // THIS WILL WORK
    }

    fun getColorType(): String {
        val color = getUpperCaseColor()
//        return if (color == "")
//            "empty"
//        else if (color == "RED" || color == "BLUE" || color == "GREEN")
//            "rgb"
//        else
//            "other"
        return when (color) {
            "" -> "empty"
            "RED", "GREEN", "BLUE" ->  "rgb"
            else -> "other"
        }
    }

    fun getUpperCaseColor(): String {
        return favoriteColor?.toUpperCase() ?: ""
    }

    override fun toString() =
        "$title $firstName $surname"

    companion object {

        fun getAge(dateOfBirth: Calendar?): Int? {
            if (dateOfBirth === null) {
                return null
            }

            val today: Calendar = GregorianCalendar()
            var years: Int = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)
            return if (dateOfBirth.get(Calendar.DAY_OF_YEAR) >= today.get(Calendar.YEAR)) {
                years - 1
            } else {
                years
            }
        }

    }

    val age: Int?
        get() = getAge(this.dateOfBirth)

    // If age is not null then return age otherwise -1
    // This similar to if (age != null) age else -1
    // Using the elvis operator
    val safeAge: Int
        get() = age ?: -1

}