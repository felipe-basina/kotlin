package spring.lab.coroutine

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import spring.lab.forkjoin.model.BeanFJ

@Service
class IncrementCoroutineService {

    private val log = LoggerFactory.getLogger(IncrementCoroutineService::class.java)

    fun increment(beanFJ: BeanFJ): BeanFJ {
        //log.info("thread name ${Thread.currentThread().name}")
        val random = kotlin.random.Random.nextLong(1, 3)
        beanFJ.incrementBy(random)
        //delay(1 * 1000)
        return beanFJ
    }

}