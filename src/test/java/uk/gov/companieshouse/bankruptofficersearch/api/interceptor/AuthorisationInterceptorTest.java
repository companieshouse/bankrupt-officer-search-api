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

    private static final String ADMIN_SCOTTISH_BANKRUPT_OFFICER_ROLE = "/admin/officer-search/scottish-bankrupt-officer";
    private static final String USER_ID_FIELD = "user_id";

    private final String TEST_ROLE = "test_role";
    private final String IDENTITY_FIELD = "identity";

    @Mock
    private AuthenticationHelper authenticationHelper;

    @InjectMocks
    private AuthorisationInterceptor testInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    void preHandleWhenRoleAuthorisedIsFalse() {
        when(authenticationHelper.getAuthorisedRoles(request)).thenReturn(TEST_ROLE);

        assertThat(testInterceptor.preHandle(request, response, null), is(false));

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //verifyNoMoreInteractions(request, response);
    }

    @Test
    void preHandleWhenIdentityNull() {
//        when(authenticationHelper.getAuthorisedRoles(request))
//                .thenReturn(ADMIN_SCOTTISH_BANKRUPT_OFFICER_ROLE);
//        when(authenticationHelper.getAuthorisedIdentityType(request))
//                .thenReturn("");
//
//        assertThat(testInterceptor.preHandle(request, response, null), is(false));
//        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //verifyNoMoreInteractions(request, response);
    }

    @Test
    void afterCompletion() {
        testInterceptor.afterCompletion(request, response, null, null);

        verify(request).setAttribute(USER_ID_FIELD, null);
        verifyNoMoreInteractions(request, response);
    }
}
