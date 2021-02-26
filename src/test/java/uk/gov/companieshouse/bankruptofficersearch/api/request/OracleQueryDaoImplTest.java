package uk.gov.companieshouse.bankruptofficersearch.api.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;

@ExtendWith(MockitoExtension.class)
public class OracleQueryDaoImplTest {

    private static final String ORACLE_QUERY_API_TEST_URL = "http://oracle-query-api";

    private static final String OFFICERS_URI = "/officer-search/scottish-bankrupt-officers";

    private static final String OFFICER_ID = "1234567890";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ScottishBankruptOfficerSearchEntity searchEntity;

    @InjectMocks
    private OracleQueryDaoImpl oracleQueryDao;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(oracleQueryDao, "oracleQueryApiUrl", ORACLE_QUERY_API_TEST_URL);
    }

    @Test
    void testGetScottishBankruptOfficers() {
        ScottishBankruptOfficerSearchResultsEntity searchResults = new ScottishBankruptOfficerSearchResultsEntity();

        when(restTemplate.postForObject(ORACLE_QUERY_API_TEST_URL + OFFICERS_URI, searchEntity, ScottishBankruptOfficerSearchResultsEntity.class)).thenReturn(searchResults);

        ScottishBankruptOfficerSearchResultsEntity result = oracleQueryDao.getScottishBankruptOfficers(searchEntity);

        assertEquals(searchResults, result);
    }

    @Test
    void testGetScottishBankruptOfficer() {
        ScottishBankruptOfficerDetailsEntity officerDetails = new ScottishBankruptOfficerDetailsEntity();

        when(restTemplate.getForObject(ORACLE_QUERY_API_TEST_URL + OFFICERS_URI + "/" + OFFICER_ID, ScottishBankruptOfficerDetailsEntity.class)).thenReturn(officerDetails);

        ScottishBankruptOfficerDetailsEntity result = oracleQueryDao.getScottishBankruptOfficer(OFFICER_ID);

        assertEquals(officerDetails, result);
    }
}