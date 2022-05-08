package spring.lab.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class CustomAspectComponent {

    @Around(value = "@annotation(AuthorizedOnly)")
    fun customAspect(pjp: ProceedingJoinPoint): Any? {
        val authorizationComponent = pjp.args[0] as AuthorizationComponent
        val param = pjp.args[1] as ParamObj
        val canInvoke = authorizationComponent.canInvoke(param)
        if (canInvoke) {
            println("Can invoke function....")
            return pjp.proceed()
        }
        throw RuntimeException("Can not invoke function with param=$param")
    }

}