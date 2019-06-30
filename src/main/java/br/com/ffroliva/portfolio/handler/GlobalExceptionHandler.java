package br.com.ffroliva.portfolio.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class GlobalExceptionHandler {
    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";

	@ExceptionHandler(TransactionSystemException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleError(final TransactionSystemException tse) {
		log.info("Transaction exception, handling error" + tse);
        if (tse.getCause() instanceof RollbackException) {
            final RollbackException re = (RollbackException) tse.getCause();

            if (re.getCause() instanceof ConstraintViolationException) {
                return handleError((ConstraintViolationException) re.getCause());
            }
        }
        throw tse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleError(final ConstraintViolationException cve) {
    	log.info("Constraint Violatin Exception, handling error" + cve);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        for (final ConstraintViolation<?> v : cve.getConstraintViolations())
        	if(v != null){
        		return new ResponseEntity<>(new Object() {
        			public String getErrorCode() {
        				return VALIDATION_ERROR;
        			}
        			
        			public String getMessage() {
        				return v.getMessage();
        			}
        		},headers, HttpStatus.BAD_REQUEST);
        	}
        throw cve;
    }
}
