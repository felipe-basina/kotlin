package spring.lab.data

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import spring.lab.data.models.Abominal
import spring.lab.data.models.Child
import spring.lab.data.models.Fake
import spring.lab.data.models.Relation
import spring.lab.data.repository.AbominalRepository
import spring.lab.data.repository.FakeRepository
import spring.lab.data.repository.RelationRepository
import spring.lab.data.service.MultipleEntitiesService
import kotlin.system.exitProcess

@SpringBootApplication
class KotlinSpringLabApplication

fun main(args: Array<String>) {
	val applicationContext = runApplication<KotlinSpringLabApplication>(*args)
	try {
		//createData(applicationContext)
		val service = applicationContext.getBean(MultipleEntitiesService::class.java)
		service.getMultipleEntities()
	} catch (ex: Exception) {
		ex.printStackTrace()
	}
	exitProcess(1)
}

fun createData(applicationContext: ApplicationContext) {

	val fakeRepository = applicationContext.getBean(FakeRepository::class.java)
	val abominalRepository = applicationContext.getBean(AbominalRepository::class.java)
	val relationRepository = applicationContext.getBean(RelationRepository::class.java)

	// Fake
	var fake = Fake("fakiee")
	fake = fakeRepository.save(fake)

	var abominal = Abominal("Strange name")
	abominal = abominalRepository.save(abominal)

	// Relation
	var relation = Relation("The relation", 1001, "Great things are coming")
	relation.fake = fake
	relation.abominal = abominal

	// Childs
	val child1 = Child("Child1", "First description", true)
	child1.relation = relation

	val child2 = Child("Child2", "Second description", false)
	child2.relation = relation

	relation.addChild(child1)
	relation.addChild(child2)

	relation = relationRepository.save(relation)

	fake.addRelations(relation)
	fakeRepository.save(fake)

	abominal.addRelations(relation)
	abominalRepository.save(abominal)
}
