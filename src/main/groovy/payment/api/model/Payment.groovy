package payment.api.model

import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import payment.api.exception.UnprocessableEntityException

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.Valid
import javax.validation.constraints.NotNull

import static javax.persistence.EnumType.STRING


@Entity
@Table(name = "payment")
class Payment {

    @Id
    @Column
    @GeneratedValue
    Integer id

    @ManyToOne
    @NotNull
    @JoinColumn(name="client_id")
    Client client

    @Column
    @NotNull
    BigDecimal amount

    @Column
    @NotNull
    @Enumerated(STRING)
    PaymentType type

    @Column
    @Enumerated(STRING)
    PaymentStatus status

    @Valid
    @NotNull
    @Embedded
    Buyer buyer

    @Valid
    @Embedded
    Card card

    @Column
    String boletoNumber

    def validateCard() {
        if(card == null) throw new UnprocessableEntityException('Card is required')
        card.validate()
    }
}
