package payment.api.exception

import groovy.transform.InheritConstructors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@InheritConstructors
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends BaseException{}
