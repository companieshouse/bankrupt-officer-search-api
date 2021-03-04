package uk.gov.companieshouse.bankruptofficersearch.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.OracleQueryApiException;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResult;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;
import uk.gov.companieshouse.bankruptofficersearch.api.service.impl.ScottishBankruptOfficerSearchServiceImpl;

@ExtendWith(MockitoExtension.class)
class ScottishBankruptOfficerControllerTest {

    private static final String EPHEMERAL_KEY = "0123456";

    @Mock
    private ScottishBankruptOfficerSearchServiceImpl service;

    @InjectMocks
    private ScottishBankruptOfficerController controller;

    @Test
    @DisplayName("No officers found")
    void testNoOfficersFound() throws OracleQueryApiException {
        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        when(service.searchScottishBankruptOfficers(search)).thenReturn(null);

        ResponseEntity<ScottishBankruptOfficerSearchResults> response = controller.searchScottishBankruptOfficers(search);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Officers found")
    void testOfficersFound() throws OracleQueryApiException {
        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        ScottishBankruptOfficerSearchResults results = new ScottishBankruptOfficerSearchResults();
        ArrayList<ScottishBankruptOfficerSearchResult> officerList = new ArrayList<>();
        ScottishBankruptOfficerSearchResult officer = new ScottishBankruptOfficerSearchResult();
        officerList.add(officer);
        results.setItems(officerList);

        when(service.searchScottishBankruptOfficers(search)).thenReturn(results);

        ResponseEntity<ScottishBankruptOfficerSearchResults> response = controller.searchScottishBankruptOfficers(search);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(results);
        assertNotNull(results.getItems());
        assertEquals(officer, results.getItems().get(0));
    }

    @Test
    @DisplayName("Officer found by id")
    void testOfficerFoundById() throws OracleQueryApiException {
        ScottishBankruptOfficerDetails result = new ScottishBankruptOfficerDetails();

        when(service.getScottishBankruptOfficer(EPHEMERAL_KEY)).thenReturn(result);

        ResponseEntity<ScottishBankruptOfficerDetails> response = controller.getOfficerById(EPHEMERAL_KEY);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(result, response.getBody());
    }

    @Test
    @DisplayName("No Officer found by id")
    void testNoOfficerFoundById() throws OracleQueryApiException {
        ScottishBankruptOfficerDetails search = new ScottishBankruptOfficerDetails();

        when(service.getScottishBankruptOfficer(search.getEphemeralKey())).thenReturn(null);

        ResponseEntity<ScottishBankruptOfficerDetails> response = controller.getOfficerById(search.getEphemeralKey());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}
