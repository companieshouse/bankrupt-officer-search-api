package uk.gov.companieshouse.bankruptofficersearch.api.service;

import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;

public interface BankruptOfficerSearchService {

	ScottishBankruptOfficerSearchResults getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search);

}
