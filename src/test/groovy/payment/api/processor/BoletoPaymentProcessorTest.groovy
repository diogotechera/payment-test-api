package payment.api.processor

import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import payment.api.SpockApplicationTest
import payment.api.model.Payment
import payment.api.model.PaymentStatus

class BoletoPaymentProcessorTest extends SpockApplicationTest {

    @Autowired
    BoletoPaymentProcessor boletoPaymentProcessor

    def "when process a payment, should generate a boletoNumber"() {
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')

        when:
        boletoPaymentProcessor.process(payment)

        then:
        payment.boletoNumber
        payment.status == PaymentStatus.AWAITING_PAYMENT
    }
}
