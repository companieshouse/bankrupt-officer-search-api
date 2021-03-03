package uk.gov.companieshouse.bankruptofficersearch.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;
import uk.gov.companieshouse.bankruptofficersearch.api.request.OracleQueryDaoImpl;
import uk.gov.companieshouse.bankruptofficersearch.api.service.BankruptOfficerSearchService;
import uk.gov.companieshouse.bankruptofficersearch.api.transformer.ScottishBankruptOfficerTransformer;

@Service
public class ScottishBankruptOfficerSearchServiceImpl implements BankruptOfficerSearchService {

	@Autowired
	private ScottishBankruptOfficerTransformer scottishBankruptOfficerTransformer;

	@Autowired
	private OracleQueryDaoImpl oracleQueryDao;

	@Override
	public ScottishBankruptOfficerSearchResults searchScottishBankruptOfficers(ScottishBankruptOfficerSearch search) {

	    ScottishBankruptOfficerSearchEntity searchEntity = scottishBankruptOfficerTransformer.convertToSearchEntity(search);
		ScottishBankruptOfficerSearchResultsEntity details = oracleQueryDao.getScottishBankruptOfficers(searchEntity);

		return scottishBankruptOfficerTransformer.convertToSearchResults(details);
	}

	public ScottishBankruptOfficerDetails getScottishBankruptOfficer(String ephemeralId) {

		ScottishBankruptOfficerDetailsEntity officerModel = oracleQueryDao.getScottishBankruptOfficer(ephemeralId);

		if (officerModel == null) {
			return null;
		}

		return scottishBankruptOfficerTransformer.convertToDetails(officerModel);
	}
}
