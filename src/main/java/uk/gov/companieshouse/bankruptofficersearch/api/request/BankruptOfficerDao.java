package uk.gov.companieshouse.bankruptofficersearch.api.request;

import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;

public interface BankruptOfficerDao {

    /**
     * Retrieving Scottish bankrupt officer search results
     * 
     * @param search
     * @return searchResultsEntity - matching Scottish bankrupt officers with pagination data
     */
    ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search);

    /**
     * Retrieving details for a bankrupt officer
     *
     * @param officerId
     * @return officerDetailsEntity - details of a single Scottish bankrupt officer
     */
    ScottishBankruptOfficerDetailsEntity getScottishBankruptOfficer(String officerId);
}