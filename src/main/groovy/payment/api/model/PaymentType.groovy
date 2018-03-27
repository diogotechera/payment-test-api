package payment.api.model

enum PaymentType {
    BOLETO,CREDIT_CARD

    boolean isBoleto() {
        this == BOLETO
    }
}