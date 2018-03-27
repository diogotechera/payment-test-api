package payment.api.controller

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.cors.CorsConfiguration
import payment.api.model.Payment
import payment.api.service.PaymentService

import javax.validation.Valid

@Slf4j
@RestController
@CrossOrigin(origins = CorsConfiguration.ALL)
class PaymentController {

    PaymentService paymentService

    @Autowired
    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
        log.info("creating payment {}", payment)
        Payment created = paymentService.create(payment)
        ResponseEntity.created(URI.create("/payments/${payment.id}")).body(created)
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
    Payment getPayment(@PathVariable Integer id) {
        log.info("get payment {}", id)
        paymentService.findById(id)
    }

}
