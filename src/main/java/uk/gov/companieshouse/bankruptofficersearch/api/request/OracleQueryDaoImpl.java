package uk.gov.companieshouse.bankruptofficersearch.api.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;

@Component
public class OracleQueryDaoImpl implements BankruptOfficerDao {

    private static final String OFFICERS_URI = "/officer-search/scottish-bankrupt-officers";

    private static final UriTemplate OFFICER_URI = new UriTemplate(OFFICERS_URI + "/{officerId}");

    private RestTemplate restTemplate;

    @Value("${ORACLE_QUERY_API_URL}")
    private String oracleQueryApiUrl;

    @Autowired
    public OracleQueryDaoImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(final ScottishBankruptOfficerSearchEntity search) {

        return restTemplate.postForObject(oracleQueryApiUrl + OFFICERS_URI, search,
            ScottishBankruptOfficerSearchResultsEntity.class);
    }

    @Override
    public ScottishBankruptOfficerDetailsEntity getScottishBankruptOfficer(final String officerId) {
        String uri = OFFICER_URI.expand(officerId).toString();

        return restTemplate.getForObject(oracleQueryApiUrl + uri, ScottishBankruptOfficerDetailsEntity.class);
    }
}
