package payment.api.service

import org.springframework.beans.factory.annotation.Autowired
import payment.api.SpockApplicationTest
import payment.api.exception.NotFoundException

class ClientServiceTest extends SpockApplicationTest {

    @Autowired
    ClientService service

    def 'should be found by id'(){

        when:
        def found = service.findById('32204a2e-0ce8-48f4-b993-2a688ec2a912')
        then:
        found.id

    }

    def 'should throw not found when is an invalid id'(){
        when:
        service.findById('')
        then:
        thrown(NotFoundException)

    }

}
