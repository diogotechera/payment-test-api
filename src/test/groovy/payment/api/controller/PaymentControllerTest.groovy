package payment.api.controller

import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import payment.api.SpockApplicationTest
import payment.api.model.Payment
import payment.api.model.PaymentType
import payment.api.service.PaymentService
import spock.lang.Unroll

import static org.hamcrest.Matchers.notNullValue
import static org.hamcrest.core.Is.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PaymentControllerTest extends SpockApplicationTest {

    @Autowired
    PaymentService paymentService

    @Unroll
    'given a #type a payment should be created'(){
        Payment payment = Fixture.from(Payment).gimme('valid')
        payment.type = type

        when:
        def result = this.mvc.perform(post('/payments')
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(payment)))
        then:
        result.andExpect(status().isCreated())

        where:
        _ | type
        _ | PaymentType.BOLETO
        _ | PaymentType.CREDIT_CARD
    }

    def 'given a created payment should be found by id'(){
        Payment payment = Fixture.from(Payment).gimme('valid')
        def created = paymentService.create(payment)

        when:
        def result = this.mvc.perform(get('/payments/{id}',created.id)
                .contentType(MediaType.APPLICATION_JSON))
        then:
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath('$.id', is(notNullValue())))
    }


}
