package spring.lab.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class CustomAspectComponent(@Autowired val authorizationComponent: AuthorizationComponent) {

    @Around(value = "@annotation(AuthorizedOnly)")
    fun customAspect(pjp: ProceedingJoinPoint): Any? {
        val args = pjp.args
        val param = args[0]
        val canInvoke = this.authorizationComponent.canInvoke(param as ParamObj)
        if (canInvoke) {
            println("Can invoke function....")
            return pjp.proceed()
        }
        throw RuntimeException("Can not invoke function with param=$param")
    }

}