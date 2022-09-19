package spring.lab.forkjoin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@Configuration
@EnableAsync
class AsyncConfig {

    @Bean
    fun taskExecutor(): Executor? {
        val cores = Runtime.getRuntime().availableProcessors()
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = (cores / 2)
        executor.maxPoolSize = (cores / 2)
        executor.setQueueCapacity(1000)
        executor.setThreadNamePrefix("AsyncFJ-")
        executor.initialize()
        return executor
    }

}