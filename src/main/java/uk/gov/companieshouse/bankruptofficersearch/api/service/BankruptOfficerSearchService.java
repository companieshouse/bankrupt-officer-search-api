package uk.gov.companieshouse.bankruptofficersearch.api.service;

import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;

public interface BankruptOfficerSearchService {

    /**
     * Perform a search of bankrupt officers with supplied filters
     * @param search
     * @return searchResults Matched bankrupt officers + pagination data
     */
	ScottishBankruptOfficerSearchResults searchScottishBankruptOfficers(ScottishBankruptOfficerSearch search);

    /**
     * Get the details of an bankrupt officer
     * @param ephemeralId
     * @return officerDetails Details for a single officer who is bankrupt
     */
    ScottishBankruptOfficerDetails getScottishBankruptOfficer(String ephemeralId);

}
