package payment.api

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ContextConfiguration(classes = [PaymentApplication])
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class])
class SpockApplicationTest extends FixtureApplicationTest{

    @Autowired
    protected WebApplicationContext context

    protected MockMvc mvc

    void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build()
    }

    protected static String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object)
        } catch (JsonProcessingException ignored) {
            return null
        }
    }


}
