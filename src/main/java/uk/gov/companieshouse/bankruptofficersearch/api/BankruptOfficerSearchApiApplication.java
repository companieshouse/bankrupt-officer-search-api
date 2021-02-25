package uk.gov.companieshouse.bankruptofficersearch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.LoggingInterceptor;

@SpringBootApplication
public class BankruptOfficerSearchApiApplication implements WebMvcConfigurer {

    public static final String APPLICATION_NAME_SPACE = "bankrupt-officer-search-api";

    private LoggingInterceptor loggingInterceptor;

    public BankruptOfficerSearchApiApplication(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankruptOfficerSearchApiApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .excludePathPatterns("/internal/officer-search/scottish-bankrupt-officers/healthcheck");
    }
}
