package uk.gov.companieshouse.bankruptofficersearch.api.service;

import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;

public interface BankruptOfficerSearchService {

	ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search);

}
