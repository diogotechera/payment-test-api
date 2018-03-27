package payment.api.processor

import org.springframework.stereotype.Component
import payment.api.model.Payment
import payment.api.model.PaymentStatus

@Component
class CreditCardPaymentProcessor implements PaymentProcessor{

    @Override
    def process(Payment payment) {
        payment.validateCard()
        payment.status = PaymentStatus.APPROVED
    }
}
