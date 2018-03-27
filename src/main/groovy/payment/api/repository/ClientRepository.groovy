package payment.api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import payment.api.model.Client
import payment.api.model.Payment

@Repository
interface ClientRepository extends CrudRepository<Client,String>{
}