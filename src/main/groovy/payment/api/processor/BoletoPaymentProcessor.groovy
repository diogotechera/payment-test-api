package payment.api.processor

import org.springframework.stereotype.Component
import payment.api.model.Payment
import payment.api.model.PaymentStatus

@Component
class BoletoPaymentProcessor implements PaymentProcessor{

    @Override
    def process(Payment payment) {
        payment.boletoNumber = new Date().getTime()
        payment.status = PaymentStatus.AWAITING_PAYMENT
    }
}
