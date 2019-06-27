package br.com.ffroliva.portfolio.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
class GlobalExceptionHandler {
    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";

	@ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleError(final TransactionSystemException tse) {
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
        for (final ConstraintViolation<?> v : cve.getConstraintViolations())
        	if(v != null){
        		return new ResponseEntity<>(new Object() {
        			public String getErrorCode() {
        				return VALIDATION_ERROR;
        			}
        			
        			public String getMessage() {
        				return v.getMessage();
        			}
        		}, HttpStatus.BAD_REQUEST);
        	}
        throw cve;
    }
}
