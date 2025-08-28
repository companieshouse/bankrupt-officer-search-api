package uk.gov.companieshouse.bankruptofficersearch.api.request;

import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.OracleQueryApiException;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerSearchResultsEntity;

public interface BankruptOfficerDao {

    /**
     * Retrieving Scottish bankrupt officer search results
     * 
     * @param search
     * @return searchResultsEntity - matching Scottish bankrupt officers with pagination data
     */
    ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search) throws OracleQueryApiException, URIValidationException;

    /**
     * Retrieving details for a bankrupt officer
     *
     * @param officerId
     * @return officerDetailsEntity - details of a single Scottish bankrupt officer
     */
    ScottishBankruptOfficerDetailsEntity getScottishBankruptOfficer(String officerId) throws OracleQueryApiException, URIValidationException;
}