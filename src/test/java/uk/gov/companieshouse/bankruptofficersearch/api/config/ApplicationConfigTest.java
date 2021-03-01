package uk.gov.companieshouse.bankruptofficersearch.api.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class ApplicationConfigTest {

    private ApplicationConfig config;

    @BeforeEach
    void setUp() {
        config = new ApplicationConfig();
    }

    @Test
    void getBeanForRestTemplate() {
        RestTemplate restTemplate = config.getRestTemplate();
        assertNotNull(restTemplate);
    }
}
