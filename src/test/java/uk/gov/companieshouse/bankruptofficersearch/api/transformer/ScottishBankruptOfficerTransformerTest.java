package uk.gov.companieshouse.bankruptofficersearch.api.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchFilters;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResult;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;

class ScottishBankruptOfficerTransformerTest {


    private ScottishBankruptOfficerTransformer transformer;

    private static final String EPHEMERAL_KEY = "key";
    private static final String FORENAME1 = "forename";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final String ADDRESS_LINE1 = "address line 1";
    private static final String ADDRESS_LINE2 = "address line 2";
    private static final String ADDRESS_LINE3 = "address line 3";
    private static final String ADDRESS_TOWN = "address town";
    private static final String ADDRESS_COUNTY = "address county";
    private static final String ADDRESS_POSTCODE = "address postcode";
    private static final String ALIAS = "alias";
    private static final String CASE_REFERENCE = "case reference";
    private static final String CASE_TYPE = "case type";
    private static final String BANKRUPTCY_TYPE = "bankruptcy type";
    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate DEBTOR_DISCHARGE = LocalDate.now();
    private static final LocalDate TRUSTEE_DISCHARGE_DATE = LocalDate.now();
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final LocalDate FROM_DATE_OF_BIRTH = LocalDate.now();
    private static final LocalDate TO_DATE_OF_BIRTH = LocalDate.now();
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 0;

    @BeforeEach
    public void setUp() {
        transformer = new ScottishBankruptOfficerTransformer();

    }

    @Test
    @DisplayName("Convert officer result")
    void testConvertOfficerResult() {
        ScottishBankruptOfficerSearchResultEntity newSearchResult = createSearchResultEntity();
        ScottishBankruptOfficerSearchResult convertedOfficer = transformer.convertToSearchResult(newSearchResult);

        assertEquals(EPHEMERAL_KEY, convertedOfficer.getEphemeralKey());
        assertEquals(FORENAME1, convertedOfficer.getForename1());
        assertEquals(FORENAME2, convertedOfficer.getForename2());
        assertEquals(SURNAME, convertedOfficer.getSurname());
        assertEquals(ADDRESS_LINE1, convertedOfficer.getAddressLine1());
        assertEquals(ADDRESS_LINE2, convertedOfficer.getAddressLine2());
        assertEquals(ADDRESS_LINE3, convertedOfficer.getAddressLine3());
        assertEquals(ADDRESS_TOWN, convertedOfficer.getTown());
        assertEquals(ADDRESS_COUNTY, convertedOfficer.getCounty());
        assertEquals(ADDRESS_POSTCODE, convertedOfficer.getPostcode());
        assertEquals(DATE_OF_BIRTH, convertedOfficer.getDateOfBirth());
        assertEquals(DEBTOR_DISCHARGE, convertedOfficer.getDebtorDischargeDate());

    }


    @Test
    @DisplayName("Convert officer details")
    void testConvertOfficerDetails() {
        ScottishBankruptOfficerDetailsEntity newOfficer = createOfficerEntity();
        ScottishBankruptOfficerDetails convertedOfficer = transformer.convertToDetails(newOfficer);
        assertEquals(EPHEMERAL_KEY, convertedOfficer.getEphemeralKey());
        assertEquals(FORENAME1, convertedOfficer.getForename1());
        assertEquals(FORENAME2, convertedOfficer.getForename2());
        assertEquals(SURNAME, convertedOfficer.getSurname());
        assertEquals(ADDRESS_LINE1, convertedOfficer.getAddressLine1());
        assertEquals(ADDRESS_LINE2, convertedOfficer.getAddressLine2());
        assertEquals(ADDRESS_LINE3, convertedOfficer.getAddressLine3());
        assertEquals(ADDRESS_TOWN, convertedOfficer.getTown());
        assertEquals(ADDRESS_COUNTY, convertedOfficer.getCounty());
        assertEquals(ADDRESS_POSTCODE, convertedOfficer.getPostcode());
        assertEquals(DATE_OF_BIRTH, convertedOfficer.getDateOfBirth());
        assertEquals(ALIAS, convertedOfficer.getAlias());
        assertEquals(CASE_REFERENCE, convertedOfficer.getCaseReference());
        assertEquals(CASE_TYPE, convertedOfficer.getCaseType());
        assertEquals(BANKRUPTCY_TYPE, convertedOfficer.getBankruptcyType());
        assertEquals(START_DATE, convertedOfficer.getStartDate());
        assertEquals(DEBTOR_DISCHARGE, convertedOfficer.getDebtorDischargeDate());
        assertEquals(TRUSTEE_DISCHARGE_DATE, convertedOfficer.getTrusteeDischargeDate());

    }

    @Test
    @DisplayName("Convert multiple officers")
    void testConvertMultipleOfficers() {
        List<ScottishBankruptOfficerSearchResultEntity>searchResultEntityList = new ArrayList<>();

        searchResultEntityList.add(createSearchResultEntity());
        searchResultEntityList.add(createSearchResultEntity());
        searchResultEntityList.add(createSearchResultEntity());
        searchResultEntityList.add(createSearchResultEntity());
        searchResultEntityList.add(createSearchResultEntity());

        ScottishBankruptOfficerSearchResultsEntity searchResultsEntity = new ScottishBankruptOfficerSearchResultsEntity();
        searchResultsEntity.setItems(searchResultEntityList);
        searchResultsEntity.setItemsPerPage(1);
        searchResultsEntity.setStartIndex(1);
        searchResultsEntity.setTotalResults(searchResultsEntity.getItems().size());

        ScottishBankruptOfficerSearchResults convertedList = transformer.convertToSearchResults(searchResultsEntity);
        assertEquals(5, convertedList.getItems().size());
        assertEquals(5, convertedList.getTotalResults());
        assertEquals(1, convertedList.getStartIndex());
        assertEquals(1, convertedList.getItemsPerPage());
    }

    @Test
    @DisplayName("Convert to search and filter entity")
    void testConvertToSearchAndFilterEntity() {
        ScottishBankruptOfficerSearch scottishBankruptOfficerSearch = new ScottishBankruptOfficerSearch();

        ScottishBankruptOfficerSearchFilters filters = new ScottishBankruptOfficerSearchFilters();
        filters.setForename1(FORENAME1);
        filters.setSurname(SURNAME);
        filters.setFromDateOfBirth(FROM_DATE_OF_BIRTH.toString());
        filters.setToDateOfBirth(TO_DATE_OF_BIRTH.toString());
        filters.setAlias(ALIAS);
        filters.setPostcode(ADDRESS_POSTCODE);

        scottishBankruptOfficerSearch.setFilters(filters);
        scottishBankruptOfficerSearch.setStartIndex(START_INDEX);
        scottishBankruptOfficerSearch.setStartIndex(ITEMS_PER_PAGE);
        ScottishBankruptOfficerSearchEntity convertedOfficer = transformer.convertToSearchEntity(scottishBankruptOfficerSearch);

        assertEquals(START_INDEX, convertedOfficer.getStartIndex());
        assertEquals(ITEMS_PER_PAGE, convertedOfficer.getItemsPerPage());
        assertEquals(filters.getForename1(), convertedOfficer.getFilters().getForename1());
        assertEquals(filters.getSurname(), convertedOfficer.getFilters().getSurname());
        assertEquals(filters.getFromDateOfBirth(), convertedOfficer.getFilters().getFromDateOfBirth());
        assertEquals(filters.getToDateOfBirth(), convertedOfficer.getFilters().getToDateOfBirth());
        assertEquals(filters.getAlias(), convertedOfficer.getFilters().getAlias());
        assertEquals(filters.getPostcode(), convertedOfficer.getFilters().getPostcode());
    }

    private ScottishBankruptOfficerDetailsEntity createOfficerEntity() {

        ScottishBankruptOfficerDetailsEntity detailsEntity = new ScottishBankruptOfficerDetailsEntity();

        detailsEntity.setEphemeralKey(EPHEMERAL_KEY);
        detailsEntity.setForename1(FORENAME1);
        detailsEntity.setForename2(FORENAME2);
        detailsEntity.setSurname(SURNAME);
        detailsEntity.setAddressLine1(ADDRESS_LINE1);
        detailsEntity.setAddressLine2(ADDRESS_LINE2);
        detailsEntity.setAddressLine3(ADDRESS_LINE3);
        detailsEntity.setTown(ADDRESS_TOWN);
        detailsEntity.setCounty(ADDRESS_COUNTY);
        detailsEntity.setPostcode(ADDRESS_POSTCODE);
        detailsEntity.setDateOfBirth(DATE_OF_BIRTH);
        detailsEntity.setAlias(ALIAS);
        detailsEntity.setCaseReference(CASE_REFERENCE);
        detailsEntity.setCaseType(CASE_TYPE);
        detailsEntity.setBankruptcyType(BANKRUPTCY_TYPE);
        detailsEntity.setStartDate(START_DATE);
        detailsEntity.setDebtorDischargeDate(DEBTOR_DISCHARGE);
        detailsEntity.setTrusteeDischargeDate(TRUSTEE_DISCHARGE_DATE);

        return detailsEntity;

    }

    private ScottishBankruptOfficerSearchResultEntity createSearchResultEntity() {

        ScottishBankruptOfficerSearchResultEntity searchResultEntity = new ScottishBankruptOfficerSearchResultEntity();

        searchResultEntity.setEphemeralKey(EPHEMERAL_KEY);
        searchResultEntity.setForename1(FORENAME1);
        searchResultEntity.setForename2(FORENAME2);
        searchResultEntity.setSurname(SURNAME);
        searchResultEntity.setDateOfBirth(DATE_OF_BIRTH);
        searchResultEntity.setDebtorDischargeDate(DEBTOR_DISCHARGE);
        searchResultEntity.setPostcode(ADDRESS_POSTCODE);
        searchResultEntity.setAddressLine1(ADDRESS_LINE1);
        searchResultEntity.setAddressLine2(ADDRESS_LINE2);
        searchResultEntity.setAddressLine3(ADDRESS_LINE3);
        searchResultEntity.setCounty(ADDRESS_COUNTY);
        searchResultEntity.setTown(ADDRESS_TOWN);
        return searchResultEntity;
    }

}
