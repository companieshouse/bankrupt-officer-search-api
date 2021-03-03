package uk.gov.companieshouse.bankruptofficersearch.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class OracleQueryApiException extends HttpClientErrorException {

    public OracleQueryApiException(final HttpStatus statusCode, final String statusText) {
        super(statusCode, statusText);
    }
}
