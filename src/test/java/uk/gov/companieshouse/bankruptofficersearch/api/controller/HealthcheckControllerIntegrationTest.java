package uk.gov.companieshouse.bankruptofficersearch.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
class HealthcheckControllerIntegrationTest {
    @InjectMocks
    private HealthcheckController controller;

    @Test
    @DisplayName("Successfully returns health status")
    public void returnHealthStatusSuccessfully() throws Exception {
        final ResponseEntity<Void> response = controller.getHealthCheck();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
