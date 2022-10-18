package spring.lab.coroutine

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import spring.lab.forkjoin.model.BeanFJ

@Service
class IncrementCoroutineService {

    private val log = LoggerFactory.getLogger(IncrementCoroutineService::class.java)

    suspend fun increment(beanFJ: BeanFJ): BeanFJ {
//        log.info("thread name ${Thread.currentThread().name}")
        val random = kotlin.random.Random.nextLong(1, 10)
        beanFJ.incrementBy(random)
        delay(random * 1000)
        return beanFJ
    }

}