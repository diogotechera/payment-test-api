package payment.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import payment.api.exception.NotFoundException
import payment.api.model.Payment
import payment.api.model.PaymentType
import payment.api.processor.BoletoPaymentProcessor
import payment.api.processor.CreditCardPaymentProcessor
import payment.api.processor.PaymentProcessor
import payment.api.repository.PaymentRepository

@Service
class PaymentService {

    PaymentRepository repository
    BoletoPaymentProcessor boletoPaymentProcessor
    CreditCardPaymentProcessor creditCardPaymentProcessor
    ClientService clientService

    @Autowired
    PaymentService(PaymentRepository repository, BoletoPaymentProcessor boletoPaymentProcessor,
                   CreditCardPaymentProcessor creditCardPaymentProcessor, ClientService clientService){
        this.repository = repository
        this.boletoPaymentProcessor = boletoPaymentProcessor
        this.creditCardPaymentProcessor = creditCardPaymentProcessor
        this.clientService = clientService
    }

    Payment create(Payment payment) {
        validateReferences(payment)
        process(payment)
        repository.save(payment)
    }

    def validateReferences(Payment payment) {
        payment.client = clientService.findById(payment.client.id)
    }

    def process(Payment payment) {
        getProcessorFor(payment.type).process(payment)
    }

    PaymentProcessor getProcessorFor(PaymentType paymentType) {
        paymentType.isBoleto() ? boletoPaymentProcessor : creditCardPaymentProcessor
    }

    Payment findById(Integer id) {
        def payment = repository.findById(id)
        payment.orElseThrow({new NotFoundException('Payment not found')})
    }
}
