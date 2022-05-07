package spring.lab.aspect

import org.springframework.stereotype.Service

@Service
class FakeAService {

    @AuthorizedOnly
    fun fakeA(paramObj: ParamObj) {
        println("Called fakeA function with param=${paramObj.param}")
    }

}

data class ParamObj(val param: Int)