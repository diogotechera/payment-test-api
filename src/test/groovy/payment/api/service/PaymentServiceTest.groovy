package payment.api.service

import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import payment.api.SpockApplicationTest
import payment.api.exception.NotFoundException
import payment.api.model.Payment
import payment.api.model.PaymentType
import payment.api.processor.BoletoPaymentProcessor
import payment.api.processor.CreditCardPaymentProcessor
import spock.lang.Unroll

class PaymentServiceTest extends SpockApplicationTest {

    @Autowired
    PaymentService service
    BoletoPaymentProcessor boletoPaymentProcessor = Mock(BoletoPaymentProcessor)

    CreditCardPaymentProcessor cardPaymentProcessor = Mock(CreditCardPaymentProcessor)
    @Override
    void setup() {
        service.creditCardPaymentProcessor = cardPaymentProcessor
        service.boletoPaymentProcessor = boletoPaymentProcessor
    }

    @Unroll
    'given a #type a payment should be created'(){
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')
        payment.type = type

        when:
        def created = service.create(payment)

        then:
        created.id

        where:
        _ | type
        _ | PaymentType.BOLETO
        _ | PaymentType.CREDIT_CARD
    }

    def 'given a BOLETO type should call the right processor'(){
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')
        payment.type = PaymentType.BOLETO

        when:
        def created = service.create(payment)

        then:
        created.id
        1 * boletoPaymentProcessor.process(_)

    }

    def 'given a CREDIT_CARD type should call the right processor'(){
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')
        payment.type = PaymentType.CREDIT_CARD

        when:
        def created = service.create(payment)

        then:
        created.id
        1 * cardPaymentProcessor.process(_)

    }

    def 'should be found by id'(){
        given:
        Payment payment = Fixture.from(Payment).gimme('valid')
        def created = service.create(payment)
        when:
        def found = service.findById(created.id)
        then:
        found.id

    }

    def 'should throw not found when is an invalid id'(){
        when:
        service.findById(0)
        then:
        thrown(NotFoundException)

    }

}
