package payment.api.template

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.function.impl.CpfFunction
import br.com.six2six.fixturefactory.loader.TemplateLoader
import payment.api.model.Buyer
import payment.api.model.Card
import payment.api.model.Client
import payment.api.model.Payment
import payment.api.model.PaymentType

class PaymentTemplateLoader implements TemplateLoader {

    @Override
    void load() {

        Fixture.of(Payment).addTemplate('valid', new Rule(){{
            add('amount', random(BigDecimal,0.01,10.0,1000.99,10000.9))
            add('type', random(PaymentType))
            add('client', one(Client, 'valid'))
            add('buyer', one(Buyer, 'valid'))
            add('card', one(Card, 'valid'))
        }})

        Fixture.of(Buyer).addTemplate('valid', new Rule(){{
            add('name', firstName())
            add('email', random('teste@teste.com', 'joao@gmail.com.br', 'david@terra.com.br', 'ze@org.me'))
            add('cpf', new CpfFunction())
        }})

        Fixture.of(Card).addTemplate('valid', new Rule(){{
            add('expirationMonth', random('8', '12', '2'))
            add('expirationYear', random('2025', '2020', '2030'))
            add('holderName', firstName())
            add('number', random('36000000000008', '378282000000008'))
            add('cvv', regex("\\d{3}"))
        }})

        Fixture.of(Client).addTemplate('valid', new Rule(){{
            add('id', '32204a2e-0ce8-48f4-b993-2a688ec2a912')
        }})


    }
}
