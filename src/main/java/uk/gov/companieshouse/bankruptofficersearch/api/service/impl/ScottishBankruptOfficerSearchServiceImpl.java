package uk.gov.companieshouse.bankruptofficersearch.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchFiltersEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;
import uk.gov.companieshouse.bankruptofficersearch.api.request.OracleQueryDaoImpl;
import uk.gov.companieshouse.bankruptofficersearch.api.service.BankruptOfficerSearchService;
import uk.gov.companieshouse.bankruptofficersearch.api.transformer.ScottishBankruptOfficerTransformer;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class ScottishBankruptOfficerSearchServiceImpl implements BankruptOfficerSearchService {

	@Autowired
	private ScottishBankruptOfficerTransformer scottishBankruptOfficerTransformer;

	@Autowired
	private OracleQueryDaoImpl oracleQueryDao;

	@Override
	public ScottishBankruptOfficerSearchResults getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search) {

		ScottishBankruptOfficerSearchResultsEntity details = oracleQueryDao.getScottishBankruptOfficers(search);

		return scottishBankruptOfficerTransformer.convertToSearchResults(details);
	}

	public ScottishBankruptOfficerDetails getScottishBankruptOfficer(String ephemeralId) {

		Optional<ScottishBankruptOfficerDetailsEntity> officerModel = Optional.ofNullable(
            oracleQueryDao.getScottishBankruptOfficer(ephemeralId));

		if (!officerModel.isPresent()) {
			return null;
		}

		return scottishBankruptOfficerTransformer.convertToDetails(officerModel.get());
	}
}
