package uk.gov.companieshouse.bankruptofficersearch.api.request;

import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;

public interface BankruptOfficerDao {

    ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search);

    ScottishBankruptOfficerDetailsEntity getScottishBankruptOfficer(String officerId);
}