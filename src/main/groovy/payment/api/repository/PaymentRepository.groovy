package payment.api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import payment.api.model.Payment

@Repository
interface PaymentRepository extends CrudRepository<Payment,Integer>{

}