package uk.gov.companieshouse.bankruptofficersearch.api.interceptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.companieshouse.bankruptofficersearch.api.util.AuthenticationHelper;
import uk.gov.companieshouse.logging.Logger;

@ExtendWith(MockitoExtension.class)
public class AuthorisationInterceptorTest {

    private final String USER_ID_FIELD = "user_id";

    @InjectMocks
    private AuthorisationInterceptor testInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Logger logger;

    @Test
    void preHandleWhenRoleAuthorisedIsFalse() {
        when(AuthenticationHelper.isRoleAuthorised(request)).thenReturn(false);

        assertThat(testInterceptor.preHandle(request, response, null), is(false));

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verifyNoMoreInteractions(request, response);
    }

    @Test
    void afterCompletion() {
        testInterceptor.afterCompletion(request, response, null, null);

        verify(request).setAttribute(USER_ID_FIELD, null);
        verifyNoMoreInteractions(request, response);
    }
}
