package spring.lab.forkjoin

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import spring.lab.forkjoin.model.BeanFJ
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

@Service
class IncrementService {

    private val log = LoggerFactory.getLogger(IncrementService::class.java)

    @Async
    fun increment(beanFJ: BeanFJ): Future<BeanFJ> {
        val tName = Thread.currentThread().name
        log.info("thread name $tName")
        val random = kotlin.random.Random.nextLong(1, 3)
        beanFJ.incrementBy(random)
        this.doADelay(random)
        return AsyncResult(beanFJ)
    }

    @Async
    fun incrementV2(beanFJ: BeanFJ): CompletableFuture<BeanFJ> {
        val tName = Thread.currentThread().name
        //log.info("thread name $tName")
        val random = kotlin.random.Random.nextLong(1, 6)
        beanFJ.incrementBy(random)
        this.doADelay(random)
        return CompletableFuture.completedFuture(beanFJ)
    }

    private fun doADelay(delay: Long) {
        Thread.sleep(1 * 1000)
    }

}