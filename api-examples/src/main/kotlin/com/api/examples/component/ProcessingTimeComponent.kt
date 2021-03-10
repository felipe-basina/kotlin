package com.api.examples.component

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime

@Aspect
@Component
class ProcessingTimeComponent {

    @Around("@annotation(LogExecutionTime)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val initTime = System.currentTimeMillis()
        var proceed = joinPoint.proceed()
        println("${joinPoint.signature} executed in ${System.currentTimeMillis() - initTime}(ms)")
        return proceed
    }

}