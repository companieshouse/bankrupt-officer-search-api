package uk.gov.companieshouse.bankruptofficersearch.api.interceptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.companieshouse.bankruptofficersearch.api.util.AuthenticationHelper;

@ExtendWith(MockitoExtension.class)
class AuthorisationInterceptorTest {

    private static final String USER_ID_FIELD = "user_id";
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
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(false);

        assertThat(testInterceptor.preHandle(request, response, null), is(false));

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(authenticationHelper, never()).getAuthorisedUser(request);
        verify(authenticationHelper, never()).getAuthorisedIdentity(request);
        verify(authenticationHelper, never()).getAuthorisedIdentityType(request);
    }

    @Test
    void preHandleWhenIdentityNull() {
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(true);
        when(authenticationHelper.getAuthorisedIdentityType(request)).thenReturn("");

        assertThat(testInterceptor.preHandle(request, response, null), is(false));
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(authenticationHelper, never()).getAuthorisedUser(request);
        verify(authenticationHelper, never()).getAuthorisedIdentity(request);
    }

    @Test
    void preHandleWhenAuthorisedIdentityNull() {
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(true);
        when(authenticationHelper.getAuthorisedIdentityType(request)).thenReturn(AuthenticationHelper.OAUTH2_IDENTITY_TYPE);
        when(authenticationHelper.getAuthorisedIdentity(request)).thenReturn(null);

        assertThat(testInterceptor.preHandle(request, response, null), is(false));
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(authenticationHelper, never()).getAuthorisedUser(request);
    }

    @Test
    void preHandleWhenAuthorisedIdentityEmpty() {
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(true);
        when(authenticationHelper.getAuthorisedIdentityType(request)).thenReturn(AuthenticationHelper.OAUTH2_IDENTITY_TYPE);
        when(authenticationHelper.getAuthorisedIdentity(request)).thenReturn("");

        assertThat(testInterceptor.preHandle(request, response, null), is(false));
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(authenticationHelper, never()).getAuthorisedUser(request);
    }

    @Test
    void preHandleWhenAuthorisedUserNull() {
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(true);
        when(authenticationHelper.getAuthorisedIdentityType(request)).thenReturn(AuthenticationHelper.OAUTH2_IDENTITY_TYPE);
        when(authenticationHelper.getAuthorisedIdentity(request)).thenReturn(IDENTITY_FIELD);
        when(authenticationHelper.getAuthorisedUser(request)).thenReturn(null);

        assertThat(testInterceptor.preHandle(request, response, null), is(false));
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    void preHandleWhenAuthorisedUserEmpty() {
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(true);
        when(authenticationHelper.getAuthorisedIdentityType(request)).thenReturn(AuthenticationHelper.OAUTH2_IDENTITY_TYPE);
        when(authenticationHelper.getAuthorisedIdentity(request)).thenReturn(IDENTITY_FIELD);
        when(authenticationHelper.getAuthorisedUser(request)).thenReturn(" ");

        assertThat(testInterceptor.preHandle(request, response, null), is(false));
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    void preHandleWhenOAuth2IdentityOk() {
        when(authenticationHelper.isRoleAuthorised(request)).thenReturn(true);
        when(authenticationHelper.getAuthorisedIdentityType(request)).thenReturn(AuthenticationHelper.OAUTH2_IDENTITY_TYPE);
        when(authenticationHelper.getAuthorisedIdentity(request)).thenReturn(IDENTITY_FIELD);
        when(authenticationHelper.getAuthorisedUser(request)).thenReturn("user_id");

        assertThat(testInterceptor.preHandle(request, response, null), is(true));
    }

    @Test
    void afterCompletion() {
        testInterceptor.afterCompletion(request, response, null, null);

        verify(request).setAttribute(USER_ID_FIELD, null);
        verifyNoMoreInteractions(request, response);
    }
}
