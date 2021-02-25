package uk.gov.companieshouse.bankruptofficersearch.api.exception;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testHandleException() {
        ResponseEntity entity = globalExceptionHandler.handleException(new Exception());

        assertNotNull(entity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,entity.getStatusCode());
    }
}
