package uk.gov.companieshouse.bankruptofficersearch.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.AuthorisationInterceptor;
import uk.gov.companieshouse.bankruptofficersearch.api.interceptor.LoggingInterceptor;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankruptOfficerSearchApiApplicationTest {

	@Mock
	private LoggingInterceptor loggingInterceptor;

	@Mock
	private AuthorisationInterceptor authorisationInterceptor;

	@Mock
	private InterceptorRegistry interceptorRegistry;

	@Mock
	private InterceptorRegistration interceptorRegistration;

	@InjectMocks
	private BankruptOfficerSearchApiApplication application;

	@Test
	void testAddInterceptors() {
		doReturn(interceptorRegistration).when(interceptorRegistry).addInterceptor(loggingInterceptor);
		doReturn(interceptorRegistration).when(interceptorRegistry).addInterceptor(authorisationInterceptor);

		when(interceptorRegistration.addPathPatterns(anyString())).thenReturn(interceptorRegistration);
		when(interceptorRegistration.excludePathPatterns(anyString())).thenReturn(interceptorRegistration);

		application.addInterceptors(interceptorRegistry);

		InOrder inOrder = Mockito.inOrder(interceptorRegistry);
		inOrder.verify(interceptorRegistry).addInterceptor(loggingInterceptor);
		inOrder.verify(interceptorRegistry).addInterceptor(authorisationInterceptor);
		verifyNoMoreInteractions(interceptorRegistry);
	}

}
