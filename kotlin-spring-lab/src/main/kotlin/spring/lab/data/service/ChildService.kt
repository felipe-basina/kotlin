package spring.lab.data.service

import org.springframework.stereotype.Service
import spring.lab.data.models.Child
import spring.lab.data.models.Relation
import kotlin.random.Random

@Service
class ChildService {

    fun new(flag: Boolean = false, relation: Relation): Child {
        val randomInt = Random(100).nextInt(1, 99)
        val child = Child(
            "A child $randomInt",
            "Child desc $randomInt:$randomInt",
            flag
        )
        child.relation = relation
        return child
    }

}