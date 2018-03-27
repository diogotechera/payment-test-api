package payment.api.processor

import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import payment.api.SpockApplicationTest
import payment.api.exception.UnprocessableEntityException
import payment.api.model.Payment
import payment.api.model.PaymentStatus

class CreditCardPaymentProcessorTest extends SpockApplicationTest {

    @Autowired
    CreditCardPaymentProcessor cardPaymentProcessor

    def "when process a payment, should require a card"() {
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')
        payment.card = null

        when:
        cardPaymentProcessor.process(payment)

        then:
        def ex = thrown(UnprocessableEntityException)
        ex.message  == 'Card is required'

    }

    def "when process a payment, should validate a card expiration date"() {
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')
        payment.card.expirationYear = '2000'

        when:
        cardPaymentProcessor.process(payment)

        then:
        def ex = thrown(UnprocessableEntityException)
        ex.message  == 'Card is expired'

    }

    def "should process a card payment"() {
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')


        when:
        cardPaymentProcessor.process(payment)

        then:
        payment.status == PaymentStatus.APPROVED

    }

}
