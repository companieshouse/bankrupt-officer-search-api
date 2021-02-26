package uk.gov.companieshouse.bankruptofficersearch.api.exception;

import static uk.gov.companieshouse.bankruptofficersearch.api.BankruptOfficerSearchApiApplication.APPLICATION_NAME_SPACE;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAME_SPACE);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        HashMap<String, Object> message = new HashMap<>();
        message.put("message", ex.getMessage());
        message.put("error", ex.getClass());
        LOGGER.error(ex.getMessage(), message);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}