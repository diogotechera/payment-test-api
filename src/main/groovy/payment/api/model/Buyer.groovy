package payment.api.model

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Buyer {

    @NotNull
    @Column(name= 'buyer_name')
    String name

    @NotNull
    @Column(name= 'buyer_email')
    String email

    @NotNull
    @Column(name= 'buyer_cpf')
    String cpf
}
