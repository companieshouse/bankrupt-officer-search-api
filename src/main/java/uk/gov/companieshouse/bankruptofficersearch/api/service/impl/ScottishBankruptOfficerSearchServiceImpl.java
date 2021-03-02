package uk.gov.companieshouse.bankruptofficersearch.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchFiltersEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.service.BankruptOfficerSearchService;
import uk.gov.companieshouse.bankruptofficersearch.api.transformer.ScottishBankruptOfficerTransformer;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class ScottishBankruptOfficerSearchServiceImpl implements BankruptOfficerSearchService {

	@Autowired
	private ScottishBankruptOfficerTransformer scottishBankruptOfficerTransformer;

	@Override
	public ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(ScottishBankruptOfficerSearchEntity search) {

		Pageable page = PageRequest.of(search.getStartIndex(), search.getItemsPerPage());

		ScottishBankruptOfficerSearchFiltersEntity filters = search.getFilters();

		Page<ScottishBankruptOfficerDetailsEntity> details = xxx;

		return scottishBankruptOfficerTransformer.convertToSearchResults(details);
	}

	public ScottishBankruptOfficerDetailsEntity getScottishBankruptOfficer(String ephemeralId) {

		Optional<ScottishBankruptOfficerDetailsEntity> officerModel = xxx.findById(ephemeralId);

		if (!officerModel.isPresent()) {
			return null;
		}

		return scottishBankruptOfficerTransformer.convertToDetails(officerModel.get());
	}
}
