package uk.gov.companieshouse.bankruptofficersearch.api.request;

import static uk.gov.companieshouse.bankruptofficersearch.api.BankruptOfficerSearchApiApplication.APPLICATION_NAME_SPACE;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.OracleQueryApiException;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerDetailsEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchEntity;
import uk.gov.companieshouse.bankruptofficersearch.api.model.response.ScottishBankruptOfficerSearchResultsEntity;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Component
public class OracleQueryDaoImpl implements BankruptOfficerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAME_SPACE);

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
    public ScottishBankruptOfficerSearchResultsEntity getScottishBankruptOfficers(final ScottishBankruptOfficerSearchEntity search) throws OracleQueryApiException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uri", oracleQueryApiUrl + OFFICERS_URI);
        LOGGER.debug("Calling Oracle Query API to fetch Scottish bankrupt officers", map);

        try {
            return restTemplate.postForObject(oracleQueryApiUrl + OFFICERS_URI, search,
                ScottishBankruptOfficerSearchResultsEntity.class);
        } catch (HttpClientErrorException ex) {
            map.put("status_code", ex.getStatusCode());

            if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                LOGGER.debug("Oracle query API returned not found", map);
                return null;
            }

            LOGGER.error(ex, map);
            throw new OracleQueryApiException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public ScottishBankruptOfficerDetailsEntity getScottishBankruptOfficer(final String officerId) throws OracleQueryApiException {
        String uri = OFFICER_URI.expand(officerId).toString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("uri", oracleQueryApiUrl + uri);
        LOGGER.debug( "Calling Oracle Query API to fetch a Scottish bankrupt officer", map);

        try {
            return restTemplate.getForObject(oracleQueryApiUrl + uri, ScottishBankruptOfficerDetailsEntity.class);
        } catch (HttpClientErrorException ex) {
            map.put("status_code", ex.getStatusCode());

            if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                LOGGER.debug("Oracle query API returned not found", map);
                return null;
            }

            LOGGER.error(ex, map);
            throw new OracleQueryApiException(ex.getMessage(), ex.getCause());
        }
    }
}
