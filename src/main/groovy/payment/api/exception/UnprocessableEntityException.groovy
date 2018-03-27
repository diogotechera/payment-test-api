package payment.api.exception

import groovy.transform.InheritConstructors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@InheritConstructors
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class UnprocessableEntityException extends BaseException{

}
