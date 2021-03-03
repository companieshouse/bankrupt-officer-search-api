package uk.gov.companieshouse.bankruptofficersearch.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationHelperTest {

    private static final String ADMIN_SCOTTISH_BANKRUPT_OFFICER_ROLE = "/admin/officer-search/scottish-bankrupt-officer";
    private static final String ERIC_IDENTITY             = "ERIC-Identity";
    private static final String ERIC_IDENTITY_TYPE        = "ERIC-Identity-Type";
    private static final String ERIC_AUTHORISED_USER      = "ERIC-Authorised-User";
    private static final String ERIC_AUTHORISED_ROLES     = "ERIC-Authorised-Roles";

    private static final String ROLE_1_ROLE_2 = "role-1 role-2";
    private static final String ROLE_1 = "role-1";
    private static final String ROLE_2 = "role-2";

    @InjectMocks
    private AuthenticationHelper authenticationHelper;

    @Mock
    private HttpServletRequest request;

    @Test
    void testGetAuthorisedIdentityWhenRequestNull() {
        assertThat(authenticationHelper.getAuthorisedIdentity(null), is(nullValue()));
    }

    @Test
    void testGetAuthorisedIdentityWhenRequestNotNull() {
        final String expected = "identity";

        when(request.getHeader(ERIC_IDENTITY)).thenReturn(expected);

        assertThat(authenticationHelper.getAuthorisedIdentity(request), is(expected));
    }

    @Test
    void testGetAuthorisedIdentityType() {
        final String expected = "identity-type";

        when(request.getHeader(ERIC_IDENTITY_TYPE)).thenReturn(expected);

        assertThat(authenticationHelper.getAuthorisedIdentityType(request), is(expected));
    }

    @Test
    void testGetAuthorisedUser() {
        final String expected = "authorised-user";

        when(request.getHeader(ERIC_AUTHORISED_USER)).thenReturn(expected);

        assertThat(authenticationHelper.getAuthorisedUser(request), is(expected));
    }

    @Test
    void testIsRoleAuthorised() {
        when(request.getHeader(ERIC_AUTHORISED_ROLES)).thenReturn(ADMIN_SCOTTISH_BANKRUPT_OFFICER_ROLE);

        assertThat(authenticationHelper.isRoleAuthorised(request), is(true));
    }

    @Test
    void testIsRoleAuthorisedWhenEmpty() {
        when(request.getHeader(ERIC_AUTHORISED_ROLES)).thenReturn("");

        assertThat(authenticationHelper.isRoleAuthorised(request), is(false));
    }
}
