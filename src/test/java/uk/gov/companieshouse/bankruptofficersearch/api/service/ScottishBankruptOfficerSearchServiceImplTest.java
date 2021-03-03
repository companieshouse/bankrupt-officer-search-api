package uk.gov.companieshouse.bankruptofficersearch.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchFiltersEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchFilters;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;
import uk.gov.companieshouse.bankruptofficersearch.api.request.OracleQueryDaoImpl;
import uk.gov.companieshouse.bankruptofficersearch.api.service.impl.ScottishBankruptOfficerSearchServiceImpl;
import uk.gov.companieshouse.bankruptofficersearch.api.transformer.ScottishBankruptOfficerTransformer;

@ExtendWith(MockitoExtension.class)  class ScottishBankruptOfficerSearchServiceImplTest {

    @Mock
    private OracleQueryDaoImpl dao;

    @Mock
    private ScottishBankruptOfficerTransformer transformer;

    @InjectMocks
    private ScottishBankruptOfficerSearchServiceImpl service;

    private static final int START_INDEX = 1;
    private static final int ITEMS_PER_PAGE = 2;
    private static final String FORENAME = "forename";
    private static final String SURNAME = "surname";
    private static final String DATE_OF_BIRTH = "2020-01-01";
    private static final String POSTCODE = "postcode";
    private static final String EPHEMERAL_KEY = "0123456";


    @Test
    @DisplayName("Search for bankrupt officers")
    void testScottishBankruptSearch() {
        ScottishBankruptOfficerSearchFilters searchFilters = new ScottishBankruptOfficerSearchFilters();
        searchFilters.setForename1(FORENAME);
        searchFilters.setSurname(SURNAME);
        searchFilters.setDateOfBirth(DATE_OF_BIRTH);
        searchFilters.setPostcode(POSTCODE);

        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        search.setStartIndex(START_INDEX);
        search.setItemsPerPage(ITEMS_PER_PAGE);
        search.setFilters(searchFilters);

        ScottishBankruptOfficerSearchEntity searchEntity = new ScottishBankruptOfficerSearchEntity();
        ScottishBankruptOfficerSearchResultsEntity resultsEntity = new ScottishBankruptOfficerSearchResultsEntity();
        ScottishBankruptOfficerSearchResults expectedSearchResults = new ScottishBankruptOfficerSearchResults();

        when(transformer.convertToSearchEntity(search)).thenReturn(searchEntity);
        when(dao.getScottishBankruptOfficers(searchEntity)).thenReturn(resultsEntity);
        when(transformer.convertToSearchResults(resultsEntity)).thenReturn(expectedSearchResults);

        ScottishBankruptOfficerSearchResults searchResults = service.searchScottishBankruptOfficers(search);
        assertEquals(searchResults, expectedSearchResults);
    }

    @Test
    @DisplayName("No results returned when retrieving Scottish bankrupt officers")
    void testNoResultsReturnsForScottishBankruptOfficersSearch(){
        ScottishBankruptOfficerSearchEntity mockTransformerResponse = new ScottishBankruptOfficerSearchEntity();
        when(transformer.convertToSearchEntity(any(ScottishBankruptOfficerSearch.class))).thenReturn(mockTransformerResponse);
        when(dao.getScottishBankruptOfficers(any(ScottishBankruptOfficerSearchEntity.class))).thenReturn(null);

        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        ScottishBankruptOfficerSearchResults searchResults = service.searchScottishBankruptOfficers(search);

        assertNull(searchResults);
    }

    @Test
    @DisplayName("Search for bankrupt officer by id")
    void testScottishBankruptSearchByID() {
        ScottishBankruptOfficerDetailsEntity mockRepoResponse = new ScottishBankruptOfficerDetailsEntity() {{
            setEphemeralKey(EPHEMERAL_KEY);
        }};
        when(dao.getScottishBankruptOfficer(eq(EPHEMERAL_KEY))).thenReturn(mockRepoResponse);
        ScottishBankruptOfficerDetails mockTransformerResponse = new ScottishBankruptOfficerDetails() {{
            setEphemeralKey(EPHEMERAL_KEY);

        }};
        when(transformer.convertToDetails(mockRepoResponse)).thenReturn(mockTransformerResponse);

        ScottishBankruptOfficerDetails result = service.getScottishBankruptOfficer(EPHEMERAL_KEY);
        assertEquals(EPHEMERAL_KEY, result.getEphemeralKey());
    }

    @Test
    @DisplayName("No officer found with get by ID")
    void testNoOfficerFoundByID(){
        when(dao.getScottishBankruptOfficer(ArgumentMatchers.anyString())).thenReturn(null);

        ScottishBankruptOfficerDetails details = service.getScottishBankruptOfficer(EPHEMERAL_KEY);
        assertNull(details);
    }
}
