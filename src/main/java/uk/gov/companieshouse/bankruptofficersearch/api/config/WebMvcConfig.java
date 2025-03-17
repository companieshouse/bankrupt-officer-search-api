package uk.gov.companieshouse.bankruptofficersearch.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.AuthorisationInterceptor;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.LoggingInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;
    private final AuthorisationInterceptor authorisationInterceptor;

    @Autowired
    public WebMvcConfig(LoggingInterceptor loggingInterceptor,
            AuthorisationInterceptor authorisationInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.authorisationInterceptor = authorisationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .excludePathPatterns("/internal/officer-search/scottish-bankrupt-officers/healthcheck");
        registry.addInterceptor(authorisationInterceptor)
                .excludePathPatterns("/internal/officer-search/scottish-bankrupt-officers/healthcheck");
    }

}
