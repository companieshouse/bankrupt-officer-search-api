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
}
