package payment.api.exception

import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Slf4j
@ResponseBody
@RequestMapping(produces = "application/vnd.error")
@ControllerAdvice(annotations = RestController.class)
class RestExceptionHandler {

    @Autowired
    private Environment env;

    @ExceptionHandler(BaseException.class)
    ResponseEntity<?> handle(BaseException e) {
        log.warn(e.toString())
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.class, ResponseStatus.class)
        HttpStatus status = responseStatus.value()
        Map<String, String> response = ['reason':e.message]
        return new ResponseEntity<>(response, status)
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        log.warn("invalid request params ${e.message}")
        def errors = e.bindingResult.allErrors

        def errorsResponse = errors.collectEntries{ FieldError error ->
            [error.field,error.defaultMessage]
        }
        return new ResponseEntity<Map>(errorsResponse, HttpStatus.BAD_REQUEST)
    }

}