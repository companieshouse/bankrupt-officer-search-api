package uk.gov.companieshouse.bankruptofficersearch.api.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;

@ExtendWith(MockitoExtension.class)
public class OracleQueryDaoImplTest {

    private static final String ORACLE_QUERY_API_TEST_URL = "http://oracle-query-api";

    private static final String OFFICERS_URI = "/officer-search/scottish-bankrupt-officers";

    private static final String OFFICER_ID = "1234567890";

    private static final String FORENAME_1 = "forename1";
    private static final String FORENAME_2 = "forename2";
    private static final String ALIAS = "alias";
    private static final String SURNAME = "surname";
    private static final String ADDRESS_LINE_1 = "addressLine1";
    private static final String ADDRESS_LINE_2 = "addressLine2";
    private static final String ADDRESS_LINE_3 = "addressLine3";
    private static final String TOWN = "town";
    private static final String COUNTY = "county";
    private static final String POSTCODE = "postcode";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final String CASE_REFERENCE = "caseReference";
    private static final String CASE_TYPE = "caseType";
    private static final String BANKRUPTCY_TYPE = "bankruptcyType";
    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate DEBTOR_DISCHARGE_DATE = LocalDate.now();
    private static final LocalDate TRUSTEE_DISCHARGE_DATE = LocalDate.now();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ScottishBankruptOfficerSearchEntity searchEntity;

    @InjectMocks
    private OracleQueryDaoImpl oracleQueryDao;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(oracleQueryDao, "oracleQueryApiUrl", ORACLE_QUERY_API_TEST_URL);
    }

    @Test
    public void testGetScottishBankruptOfficers() {
        ScottishBankruptOfficerSearchResultsEntity searchResults = createSearchResults();

        when(restTemplate.postForObject(ORACLE_QUERY_API_TEST_URL + OFFICERS_URI, searchEntity, ScottishBankruptOfficerSearchResultsEntity.class)).thenReturn(searchResults);

        ScottishBankruptOfficerSearchResultsEntity result = oracleQueryDao.getScottishBankruptOfficers(searchEntity);

        assertEquals(searchResults, result);
    }

    @Test
    public void testGetScottishBankruptOfficer() {
        ScottishBankruptOfficerDetailsEntity expected = createScottishBankruptOfficer();

        when(restTemplate.getForObject(ORACLE_QUERY_API_TEST_URL + OFFICERS_URI + "/" + OFFICER_ID, ScottishBankruptOfficerDetailsEntity.class)).thenReturn(expected);

        ScottishBankruptOfficerDetailsEntity result = oracleQueryDao.getScottishBankruptOfficer(OFFICER_ID);

        assertEquals(expected, result);
    }

    private ScottishBankruptOfficerDetailsEntity createScottishBankruptOfficer() {
        ScottishBankruptOfficerDetailsEntity detailsEntity = new ScottishBankruptOfficerDetailsEntity();
        detailsEntity.setEphemeralKey(OFFICER_ID);
        detailsEntity.setForename1(FORENAME_1);
        detailsEntity.setForename2(FORENAME_2);
        detailsEntity.setAlias(ALIAS);
        detailsEntity.setSurname(SURNAME);
        detailsEntity.setAddressLine1(ADDRESS_LINE_1);
        detailsEntity.setAddressLine2(ADDRESS_LINE_2);
        detailsEntity.setAddressLine3(ADDRESS_LINE_3);
        detailsEntity.setTown(TOWN);
        detailsEntity.setCounty(COUNTY);
        detailsEntity.setPostcode(POSTCODE);
        detailsEntity.setDateOfBirth(DATE_OF_BIRTH);
        detailsEntity.setCaseReference(CASE_REFERENCE);
        detailsEntity.setCaseType(CASE_TYPE);
        detailsEntity.setBankruptcyType(BANKRUPTCY_TYPE);
        detailsEntity.setStartDate(START_DATE);
        detailsEntity.setDebtorDischargeDate(DEBTOR_DISCHARGE_DATE);
        detailsEntity.setTrusteeDischargeDate(TRUSTEE_DISCHARGE_DATE);

        return detailsEntity;
    }

    private ScottishBankruptOfficerSearchResultsEntity createSearchResults() {

        ScottishBankruptOfficerSearchResultEntity resultEntity = new ScottishBankruptOfficerSearchResultEntity();
        resultEntity.setEphemeralKey(OFFICER_ID);
        resultEntity.setForename1(FORENAME_1);
        resultEntity.setForename2(FORENAME_2);
        resultEntity.setSurname(SURNAME);
        resultEntity.setAddressLine1(ADDRESS_LINE_1);
        resultEntity.setAddressLine2(ADDRESS_LINE_2);
        resultEntity.setAddressLine3(ADDRESS_LINE_3);
        resultEntity.setTown(TOWN);
        resultEntity.setCounty(COUNTY);
        resultEntity.setPostcode(POSTCODE);
        resultEntity.setDateOfBirth(DATE_OF_BIRTH);

        List<ScottishBankruptOfficerSearchResultEntity> scottishBankruptOfficerResultList = new ArrayList<>();
        scottishBankruptOfficerResultList.add(resultEntity);

        ScottishBankruptOfficerSearchResultsEntity searchResultsEntity = new ScottishBankruptOfficerSearchResultsEntity();
        searchResultsEntity.setItemsPerPage(1);
        searchResultsEntity.setStartIndex(1);
        searchResultsEntity.setTotalResults(1);
        searchResultsEntity.setItems(scottishBankruptOfficerResultList);

        return searchResultsEntity;
    }
}