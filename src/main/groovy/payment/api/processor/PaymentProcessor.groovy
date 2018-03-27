package payment.api.processor

import payment.api.model.Payment

interface PaymentProcessor {
    def process(Payment payment)
}