package uk.gov.companieshouse.bankruptofficersearch.api.transformer;

import java.util.ArrayList;
import java.util.List;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResult;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;

public class ScottishBankruptOfficerTransformer {

    public ScottishBankruptOfficerSearchResults convertToSearchResults(ScottishBankruptOfficerSearchResultsEntity resultsEntity) {
        List<ScottishBankruptOfficerSearchResult> results = new ArrayList<>();

        for (ScottishBankruptOfficerSearchResultEntity resultEntity : resultsEntity.getItems()) {
            results.add(convertToSearchResult(resultEntity));
        }
        ScottishBankruptOfficerSearchResults searchResults = new ScottishBankruptOfficerSearchResults();
        searchResults.setItems(results);
        searchResults.setItemsPerPage(resultsEntity.getItemsPerPage());
        searchResults.setStartIndex(resultsEntity.getStartIndex());
        searchResults.setTotalResults(resultsEntity.getTotalResults());
        return searchResults;
    }

    public ScottishBankruptOfficerDetails convertToDetails(ScottishBankruptOfficerDetailsEntity detailsEntity) {
        ScottishBankruptOfficerDetails details = new ScottishBankruptOfficerDetails();

        details.setForename1(detailsEntity.getForename1());
        details.setForename2(detailsEntity.getForename2());
        details.setSurname(detailsEntity.getSurname());
        details.setDateOfBirth(detailsEntity.getDateOfBirth());
        details.setPostcode(detailsEntity.getPostcode());
        details.setAddressLine1(detailsEntity.getAddressLine1());
        details.setAddressLine2(detailsEntity.getAddressLine2());
        details.setAddressLine3(detailsEntity.getAddressLine3());
        details.setCounty(detailsEntity.getCounty());
        details.setTown(detailsEntity.getTown());
        details.setAlias(detailsEntity.getAlias());
        details.setCaseReference(detailsEntity.getCaseReference());
        details.setCaseType(detailsEntity.getCaseType());
        details.setBankruptcyType(detailsEntity.getBankruptcyType());
        details.setStartDate(detailsEntity.getStartDate());
        details.setDebtorDischargeDate(detailsEntity.getDebtorDischargeDate());
        details.setTrusteeDischargeDate(detailsEntity.getTrusteeDischargeDate());
        details.setEphemeralKey(detailsEntity.getEphemeralKey());
        return details;
    }

    public ScottishBankruptOfficerSearchResult convertToSearchResult(ScottishBankruptOfficerSearchResultEntity searchResultEntity) {
        ScottishBankruptOfficerSearchResult searchResult = new ScottishBankruptOfficerSearchResult();

        searchResult.setEphemeralKey(searchResultEntity.getEphemeralKey());
        searchResult.setForename1(searchResultEntity.getForename1());
        searchResult.setForename2(searchResultEntity.getForename2());
        searchResult.setSurname(searchResultEntity.getSurname());
        searchResult.setDateOfBirth(searchResultEntity.getDateOfBirth());
        searchResult.setPostcode(searchResultEntity.getPostcode());
        searchResult.setAddressLine1(searchResultEntity.getAddressLine1());
        searchResult.setAddressLine2(searchResultEntity.getAddressLine2());
        searchResult.setAddressLine3(searchResultEntity.getAddressLine3());
        searchResult.setCounty(searchResultEntity.getCounty());
        searchResult.setTown(searchResultEntity.getTown());
        return searchResult;
    }

    public ScottishBankruptOfficerSearchResultsEntity convertToSearchResultsEntity(ScottishBankruptOfficerSearchResults results) {
        List<ScottishBankruptOfficerSearchResultEntity> searchResultEntities = new ArrayList<>();

        for (ScottishBankruptOfficerSearchResult result : results.getItems()) {
            searchResultEntities.add(convertToSearchResultEntity(result));
        }
        ScottishBankruptOfficerSearchResultsEntity searchResultsEntity = new ScottishBankruptOfficerSearchResultsEntity();
        searchResultsEntity.setItems(searchResultEntities);
        searchResultsEntity.setItemsPerPage(results.getItemsPerPage());
        searchResultsEntity.setStartIndex(results.getStartIndex());
        searchResultsEntity.setTotalResults(results.getTotalResults());
        return searchResultsEntity;
    }

    public ScottishBankruptOfficerDetailsEntity convertToDetailsEntity(ScottishBankruptOfficerDetails details){
        ScottishBankruptOfficerDetailsEntity detailsEntity = new ScottishBankruptOfficerDetailsEntity();

        detailsEntity.setForename1(details.getForename1());
        detailsEntity.setForename2(details.getForename2());
        detailsEntity.setSurname(details.getSurname());
        detailsEntity.setDateOfBirth(details.getDateOfBirth());
        detailsEntity.setPostcode(details.getPostcode());
        detailsEntity.setAddressLine1(details.getAddressLine1());
        detailsEntity.setAddressLine2(details.getAddressLine2());
        detailsEntity.setAddressLine3(details.getAddressLine3());
        detailsEntity.setCounty(details.getCounty());
        detailsEntity.setTown(details.getTown());
        detailsEntity.setAlias(details.getAlias());
        detailsEntity.setCaseReference(details.getCaseReference());
        detailsEntity.setCaseType(details.getCaseType());
        detailsEntity.setBankruptcyType(details.getBankruptcyType());
        detailsEntity.setStartDate(details.getStartDate());
        detailsEntity.setDebtorDischargeDate(details.getDebtorDischargeDate());
        detailsEntity.setTrusteeDischargeDate(details.getTrusteeDischargeDate());
        detailsEntity.setEphemeralKey(details.getEphemeralKey());
        return detailsEntity;
    }

    public ScottishBankruptOfficerSearchResultEntity convertToSearchResultEntity(ScottishBankruptOfficerSearchResult searchResult) {
        ScottishBankruptOfficerSearchResultEntity searchResultEntity = new ScottishBankruptOfficerSearchResultEntity();

        searchResultEntity.setEphemeralKey(searchResult.getEphemeralKey());
        searchResultEntity.setForename1(searchResult.getForename1());
        searchResultEntity.setForename2(searchResult.getForename2());
        searchResultEntity.setSurname(searchResult.getSurname());
        searchResultEntity.setDateOfBirth(searchResult.getDateOfBirth());
        searchResultEntity.setPostcode(searchResult.getPostcode());
        searchResultEntity.setAddressLine1(searchResult.getAddressLine1());
        searchResultEntity.setAddressLine2(searchResult.getAddressLine2());
        searchResultEntity.setAddressLine3(searchResult.getAddressLine3());
        searchResultEntity.setCounty(searchResult.getCounty());
        searchResultEntity.setTown(searchResult.getTown());

        return searchResultEntity;
    }
}
