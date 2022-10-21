package uk.gov.companieshouse.bankruptofficersearch.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.OracleQueryApiException;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.ServiceException;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchFilters;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;
import uk.gov.companieshouse.bankruptofficersearch.api.request.OracleQueryDaoImpl;
import uk.gov.companieshouse.bankruptofficersearch.api.service.impl.ScottishBankruptOfficerSearchServiceImpl;
import uk.gov.companieshouse.bankruptofficersearch.api.transformer.ScottishBankruptOfficerTransformer;

@ExtendWith(MockitoExtension.class)
class ScottishBankruptOfficerSearchServiceImplTest {

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
    private static final String DATE_OF_BIRTH = "1975-01-01";
    private static final String FROM_DATE_OF_BIRTH = "1970-01-01";
    private static final String TO_DATE_OF_BIRTH = "1980-02-02";
    private static final String ALIAS = "alias";
    private static final String POSTCODE = "postcode";
    private static final String EPHEMERAL_KEY = "0123456";



    @Test
    @DisplayName("Search for bankrupt officers")
    void testScottishBankruptSearch() throws OracleQueryApiException, ServiceException {
        ScottishBankruptOfficerSearchFilters searchFilters = new ScottishBankruptOfficerSearchFilters();
        searchFilters.setForename1(FORENAME);
        searchFilters.setSurname(SURNAME);
        searchFilters.setFromDateOfBirth(FROM_DATE_OF_BIRTH);
        searchFilters.setToDateOfBirth(TO_DATE_OF_BIRTH);
        searchFilters.setAlias(ALIAS);
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
        assertEquals(expectedSearchResults, searchResults);
    }

    @Test
    @DisplayName("Results returned when searching for Scottish bankrupt officers using DOB range filters")
    void testResultsReturnsForScottishBankruptOfficersSearchWithDOBFilters() throws OracleQueryApiException, ServiceException  {
        ScottishBankruptOfficerSearchFilters searchFilters = new ScottishBankruptOfficerSearchFilters();
        searchFilters.setFromDateOfBirth("1970-01-01");
        searchFilters.setToDateOfBirth("1980-01-01");

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
        assertEquals(expectedSearchResults, searchResults);
    }


    @Test
    @DisplayName("Results returned when searching for Scottish bankrupt officers using From DOB filters")
    void testResultsReturnsForScottishBankruptOfficersSearchWithFromFilters() throws OracleQueryApiException, ServiceException  {
        ScottishBankruptOfficerSearchFilters searchFilters = new ScottishBankruptOfficerSearchFilters();

        searchFilters.setFromDateOfBirth(FROM_DATE_OF_BIRTH);
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
        assertEquals(expectedSearchResults, searchResults);
    }

    @Test
    @DisplayName("Results returned when searching for Scottish bankrupt officers using To DOB filters")
    void testResultsReturnsForScottishBankruptOfficersSearchWithToFilters() throws OracleQueryApiException, ServiceException  {
        ScottishBankruptOfficerSearchFilters searchFilters = new ScottishBankruptOfficerSearchFilters();

        searchFilters.setToDateOfBirth(TO_DATE_OF_BIRTH);
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
        assertEquals(expectedSearchResults, searchResults);
    }


    @Test
    @DisplayName("Scottish bankrupt officers search - throws a ServiceException")
    void testSearchBankruptOfficersThrowsServiceException() throws OracleQueryApiException {
        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();

        when(dao.getScottishBankruptOfficers(any())).thenThrow(OracleQueryApiException.class);

        assertThrows(ServiceException.class, () -> service.searchScottishBankruptOfficers(search));
    }

    @Test
    @DisplayName("Search for bankrupt officer by ID")
    void testScottishBankruptSearchByID() throws OracleQueryApiException, ServiceException  {
        ScottishBankruptOfficerDetailsEntity detailsEntity = new ScottishBankruptOfficerDetailsEntity();
        ScottishBankruptOfficerDetails details = new ScottishBankruptOfficerDetails();

        when(dao.getScottishBankruptOfficer(EPHEMERAL_KEY)).thenReturn(detailsEntity);
        when(transformer.convertToDetails(detailsEntity)).thenReturn(details);

        ScottishBankruptOfficerDetails result = service.getScottishBankruptOfficer(EPHEMERAL_KEY);
        assertEquals(details, result);
    }

    @Test
    @DisplayName("No officer found with get by ID")
    void testNoOfficerFoundByID() throws OracleQueryApiException, ServiceException  {
        when(dao.getScottishBankruptOfficer(EPHEMERAL_KEY)).thenReturn(null);

        ScottishBankruptOfficerDetails details = service.getScottishBankruptOfficer(EPHEMERAL_KEY);
        assertNull(details);
    }

    @Test
    @DisplayName("Search for bankrupt officer by ID - throws a ServiceException")
    void testScottishBankruptSearchByIDThrowsServiceException() throws OracleQueryApiException {
        when(dao.getScottishBankruptOfficer(any())).thenThrow(OracleQueryApiException.class);

        assertThrows(ServiceException.class, () -> service.getScottishBankruptOfficer(EPHEMERAL_KEY));
    }
}
