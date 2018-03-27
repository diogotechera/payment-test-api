package payment.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import payment.api.exception.NotFoundException
import payment.api.model.Client
import payment.api.repository.ClientRepository

@Service
class ClientService {

    ClientRepository repository

    @Autowired
    ClientService(ClientRepository repository){
        this.repository = repository
    }

    Client findById(String id) {
        def client = repository.findById(id)
        client.orElseThrow({new NotFoundException('Client not found')})
    }
}
