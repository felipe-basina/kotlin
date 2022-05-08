package spring.lab.aspect

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FakeAServiceTest {

    @Mock
    lateinit var authorizationComponent: AuthorizationComponent

    @Autowired
    lateinit var fakeAService: FakeAService

    @BeforeEach
    fun before() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testShouldWorkWithValidParam() {
        Mockito.doReturn(true).`when`(this.authorizationComponent).canInvoke(any())
        fakeAService.fakeA(this.authorizationComponent, ParamObj(2))
    }

    @Test
    fun testShouldWorkWithInvalidParam() {
        Mockito.doReturn(true).`when`(this.authorizationComponent).canInvoke(any())
        fakeAService.fakeA(this.authorizationComponent, ParamObj(1))
    }

    @Test
    fun testShouldNotWorkWithInvalidParam() {
        val paramObj = ParamObj(1)

        Mockito.doReturn(false).`when`(this.authorizationComponent).canInvoke(paramObj)

        val thrown = Assertions.assertThrows(RuntimeException::class.java) {
            fakeAService.fakeA(this.authorizationComponent, paramObj)
        }

        Assertions.assertEquals("Can not invoke function with param=$paramObj", thrown.message);
    }

}