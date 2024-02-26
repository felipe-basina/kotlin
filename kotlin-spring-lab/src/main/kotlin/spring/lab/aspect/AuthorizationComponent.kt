package spring.lab.aspect

import org.springframework.stereotype.Component

@Component
class AuthorizationComponent {

    fun canInvoke(paramObj: ParamObj) : Boolean {
        return paramObj.param > 1
    }

}