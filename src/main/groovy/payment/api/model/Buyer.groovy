package payment.api.model

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Embeddable
class Buyer {

    @NotNull
    @Column(name= 'buyer_name')
    String name

    @Email
    @NotNull
    @Column(name= 'buyer_email')
    String email

    @NotNull
    @Pattern(regexp = '^[0-9]{11}')
    @Column(name= 'buyer_cpf')
    String cpf
}
