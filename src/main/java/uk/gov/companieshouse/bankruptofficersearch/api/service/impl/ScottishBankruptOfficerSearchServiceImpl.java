package uk.gov.companieshouse.bankruptofficersearch.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.OracleQueryApiException;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.ServiceException;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.api.model.bankruptofficer.ScottishBankruptOfficerSearchResultsEntity;
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
    public ScottishBankruptOfficerSearchResults searchScottishBankruptOfficers(ScottishBankruptOfficerSearch search) throws ServiceException {

        ScottishBankruptOfficerSearchEntity searchEntity = scottishBankruptOfficerTransformer.convertToSearchEntity(
            search);

        try {
            ScottishBankruptOfficerSearchResultsEntity details = oracleQueryDao.getScottishBankruptOfficers(searchEntity);
            if (details == null) {
                return null;
            }
            return scottishBankruptOfficerTransformer.convertToSearchResults(details);
        } catch (OracleQueryApiException | URIValidationException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public ScottishBankruptOfficerDetails getScottishBankruptOfficer(String ephemeralId) throws ServiceException {

        try {
            ScottishBankruptOfficerDetailsEntity officerModel = oracleQueryDao.getScottishBankruptOfficer(ephemeralId);
            if (officerModel == null) {
                return null;
            }
            return scottishBankruptOfficerTransformer.convertToDetails(officerModel);
        } catch (OracleQueryApiException | URIValidationException ex) {
            throw new ServiceException(ex);
        }
    }
}
