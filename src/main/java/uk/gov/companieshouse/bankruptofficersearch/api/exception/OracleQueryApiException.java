package uk.gov.companieshouse.bankruptofficersearch.api.exception;

public class OracleQueryApiException extends RuntimeException {

    public OracleQueryApiException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
