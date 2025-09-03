package uk.gov.companieshouse.bankruptofficersearch.api.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.gov.companieshouse.api.InternalApiClient;
import uk.gov.companieshouse.api.error.ApiErrorResponseException;
import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.api.handler.search.bankruptOfficer.PrivateBankruptOfficerSearchHandler;
import uk.gov.companieshouse.api.handler.search.bankruptOfficer.request.PrivateBankruptOfficersSearch;
import uk.gov.companieshouse.api.handler.search.bankruptOfficer.request.PrivateSingleBankruptOfficerSearch;
import uk.gov.companieshouse.api.model.ApiResponse;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.OracleQueryApiException;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.service.impl.ApiSdkClient;

@ExtendWith(MockitoExtension.class)
class OracleQueryDaoImplTest {

    private static final String ORACLE_QUERY_API_TEST_URL = "http://oracle-query-api";

    private static final String OFFICERS_URI = "/officer-search/scottish-bankrupt-officers";

    private static final String OFFICER_ID = "1234567890";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ScottishBankruptOfficerSearchEntity searchEntity;

    @Mock
    private HttpClientErrorException httpClientErrorException;

    @Mock
    private URIValidationException uriValidationException;

    @Mock
    private ApiSdkClient apiSdkClient;

    @Mock
    private InternalApiClient internalApiClient;

    @Mock
    private PrivateBankruptOfficerSearchHandler privateBankruptOfficerSearchHandler;

    @Mock
    private PrivateSingleBankruptOfficerSearch privateSingleBankruptOfficerSearch;

    @Mock
    private PrivateBankruptOfficersSearch privateBankruptOfficersSearch;

    @Mock
    private ApiResponse<ScottishBankruptOfficerDetailsEntity> scottishBankruptOfficerDetailsEntityApiResponse;

    @Mock
    private ApiResponse<ScottishBankruptOfficerSearchResultsEntity> scottishBankruptOfficerSearchResultsApiResponse;

    @InjectMocks
    private OracleQueryDaoImpl oracleQueryDao;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(oracleQueryDao, "oracleQueryApiUrl", ORACLE_QUERY_API_TEST_URL);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSearchScottishBankruptOfficers() throws OracleQueryApiException, URIValidationException, ApiErrorResponseException {
        ScottishBankruptOfficerSearchResultsEntity searchResults = new ScottishBankruptOfficerSearchResultsEntity();

        when(apiSdkClient.getInternalApiClient()).thenReturn(internalApiClient);
        when(internalApiClient.privateBankruptOfficerSearchHandler()).thenReturn(privateBankruptOfficerSearchHandler);
        when(privateBankruptOfficerSearchHandler.getScottishBankruptOfficers( OFFICERS_URI, searchEntity)).thenReturn(privateBankruptOfficersSearch);
        when(privateBankruptOfficersSearch.execute()).thenReturn(scottishBankruptOfficerSearchResultsApiResponse);
        when(scottishBankruptOfficerSearchResultsApiResponse.getData()).thenReturn(searchResults);

        ScottishBankruptOfficerSearchResultsEntity result = oracleQueryDao.getScottishBankruptOfficers(searchEntity);

        assertEquals(searchResults, result);
    }

    @Test
    void testSearchScottishBankruptOfficersWhenNoResultsReturned() throws OracleQueryApiException, URIValidationException, ApiErrorResponseException {


        when(apiSdkClient.getInternalApiClient()).thenReturn(internalApiClient);
        when(internalApiClient.privateBankruptOfficerSearchHandler()).thenReturn(privateBankruptOfficerSearchHandler);
        when(privateBankruptOfficerSearchHandler.getScottishBankruptOfficers( OFFICERS_URI, searchEntity)).thenReturn(privateBankruptOfficersSearch);
        when(privateBankruptOfficersSearch.execute()).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));


        ScottishBankruptOfficerSearchResultsEntity result = oracleQueryDao.getScottishBankruptOfficers(searchEntity);

        assertNull(result);
    }

    @Test
    void testSearchScottishBankruptOfficersWhenInternalServerErrorReturned() throws ApiErrorResponseException, URIValidationException {

        when(apiSdkClient.getInternalApiClient()).thenReturn(internalApiClient);
        when(internalApiClient.privateBankruptOfficerSearchHandler()).thenReturn(privateBankruptOfficerSearchHandler);
        when(privateBankruptOfficerSearchHandler.getScottishBankruptOfficers( OFFICERS_URI, searchEntity)).thenReturn(privateBankruptOfficersSearch);
        when(privateBankruptOfficersSearch.execute()).thenThrow(new ApiErrorResponseException(new HttpResponseException.Builder(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", new HttpHeaders())));

        assertThrows(OracleQueryApiException.class, () -> oracleQueryDao.getScottishBankruptOfficers(searchEntity));
    }

    @Test
    void testGetScottishBankruptOfficer() throws OracleQueryApiException, URIValidationException, ApiErrorResponseException {
        ScottishBankruptOfficerDetailsEntity officerDetails = new ScottishBankruptOfficerDetailsEntity();

        when(apiSdkClient.getInternalApiClient()).thenReturn(internalApiClient);
        when(internalApiClient.privateBankruptOfficerSearchHandler()).thenReturn(privateBankruptOfficerSearchHandler);
        when(privateBankruptOfficerSearchHandler.getSingleScottishBankruptOfficer(OFFICERS_URI + "/" + OFFICER_ID)).thenReturn(privateSingleBankruptOfficerSearch);
        when(privateSingleBankruptOfficerSearch.execute()).thenReturn(scottishBankruptOfficerDetailsEntityApiResponse);
        when(scottishBankruptOfficerDetailsEntityApiResponse.getData()).thenReturn(officerDetails);


        ScottishBankruptOfficerDetailsEntity result = oracleQueryDao.getScottishBankruptOfficer(OFFICER_ID);

        assertEquals(officerDetails, result);
    }

    /*@Test
    void testGetScottishBankruptOfficerWhenOfficerNotFound() throws OracleQueryApiException, URIValidationException {
        when(apiSdkClient.getInternalApiClient()).thenReturn(internalApiClient);
        when(internalApiClient.privateBankruptOfficerSearchHandler()).thenReturn(privateBankruptOfficerSearchHandler);
        when(privateBankruptOfficerSearchHandler.getSingleScottishBankruptOfficer(ORACLE_QUERY_API_TEST_URL + OFFICERS_URI + "/" + OFFICER_ID)).thenThrow(httpClientErrorException);


        when(restTemplate.getForObject(ORACLE_QUERY_API_TEST_URL + OFFICERS_URI + "/" + OFFICER_ID, ScottishBankruptOfficerDetailsEntity.class)).thenThrow(httpClientErrorException);
        when(httpClientErrorException.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        ScottishBankruptOfficerDetailsEntity result = oracleQueryDao.getScottishBankruptOfficer(OFFICER_ID);

        assertNull(result);
    }*/

    @Test
    void testGetScottishBankruptOfficerWhenInternalServerErrorReturned() throws ApiErrorResponseException, URIValidationException {

        when(apiSdkClient.getInternalApiClient()).thenReturn(internalApiClient);
        when(internalApiClient.privateBankruptOfficerSearchHandler()).thenReturn(privateBankruptOfficerSearchHandler);
        when(privateBankruptOfficerSearchHandler.getSingleScottishBankruptOfficer( OFFICERS_URI + "/" + OFFICER_ID)).thenReturn(privateSingleBankruptOfficerSearch);
        when(privateSingleBankruptOfficerSearch.execute()).thenThrow(new ApiErrorResponseException(new HttpResponseException.Builder(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", new HttpHeaders())));

        assertThrows(OracleQueryApiException.class, () -> oracleQueryDao.getScottishBankruptOfficer(OFFICER_ID));

    }
}