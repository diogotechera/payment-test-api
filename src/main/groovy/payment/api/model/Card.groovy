package payment.api.model

import org.hibernate.validator.constraints.CreditCardNumber
import org.hibernate.validator.constraints.Length
import payment.api.exception.UnprocessableEntityException

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Embeddable
class Card {

    @NotNull
    @Column(name= 'card_holder_name')
    String holderName

    @NotNull
    @Column(name= 'card_number')
    @Pattern(message = "Only numbers", regexp = "^[0-9]*")
    @CreditCardNumber(ignoreNonDigitCharacters=true)
    String number

    @NotNull
    @Column(name= 'card_expiration_month')
    @Pattern(message = "invalid format", regexp = "^(0?[1-9])|(1[0-2])")
    String expirationMonth

    @NotNull
    @Column(name= 'card_expiration_year')
    @Pattern(message = "invalid format", regexp = "^(20[1-9][0-9])")
    String expirationYear

    @NotNull
    @Length(min=2, max=4)
    @Column(name= 'card_cvv')
    String cvv

    def validate() {
        def expirationDate = new Date().parse('yyyy/MM',"${expirationYear}/${expirationMonth}")
        if(expirationDate < new Date()) throw new UnprocessableEntityException('Card is expired')
    }
}
