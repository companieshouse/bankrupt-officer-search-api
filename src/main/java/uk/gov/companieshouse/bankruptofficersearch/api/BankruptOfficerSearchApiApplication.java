package uk.gov.companieshouse.bankruptofficersearch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.AuthorisationInterceptor;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.LoggingInterceptor;

@SpringBootApplication
public class BankruptOfficerSearchApiApplication implements WebMvcConfigurer {

    public static final String APPLICATION_NAME_SPACE = "bankrupt-officer-search-api";

    private final LoggingInterceptor loggingInterceptor;

    private final AuthorisationInterceptor authorisationInterceptor;

    public BankruptOfficerSearchApiApplication(LoggingInterceptor loggingInterceptor, AuthorisationInterceptor authorisationInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.authorisationInterceptor = authorisationInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankruptOfficerSearchApiApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .excludePathPatterns("/internal/officer-search/scottish-bankrupt-officers/healthcheck");
        registry.addInterceptor(authorisationInterceptor)
                .addPathPatterns("/internal/officer-search/scottish-bankrupt-officers/**")
                .excludePathPatterns("/internal/officer-search/scottish-bankrupt-officers/healthcheck");
    }
}
