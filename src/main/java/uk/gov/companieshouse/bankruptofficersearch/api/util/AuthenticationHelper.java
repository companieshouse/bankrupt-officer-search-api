package uk.gov.companieshouse.bankruptofficersearch.api.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;

public class AuthenticationHelper {

    private AuthenticationHelper() {
    }

    public static final String OAUTH2_IDENTITY_TYPE      = "oauth2";

    private static final String ADMIN_SCOTTISH_BANKRUPT_OFFICER_ROLE = "/admin/officer-search/scottish-bankrupt-officer";
    private static final String ERIC_IDENTITY             = "ERIC-Identity";
    private static final String ERIC_IDENTITY_TYPE        = "ERIC-Identity-Type";
    private static final String ERIC_AUTHORISED_USER      = "ERIC-Authorised-User";
    private static final String ERIC_AUTHORISED_ROLES     = "ERIC-Authorised-Roles";

    public static String getAuthorisedIdentity(HttpServletRequest request) {
        return getRequestHeader(request, AuthenticationHelper.ERIC_IDENTITY);
    }

    public static String getAuthorisedIdentityType(HttpServletRequest request) {
        return getRequestHeader(request, AuthenticationHelper.ERIC_IDENTITY_TYPE);
    }

    public static String getAuthorisedUser(HttpServletRequest request) {
        return getRequestHeader(request, AuthenticationHelper.ERIC_AUTHORISED_USER);
    }

    public static String getAuthorisedRoles(HttpServletRequest request) {
        return getRequestHeader(request, AuthenticationHelper.ERIC_AUTHORISED_ROLES);
    }

    public static String[] getAuthorisedRolesArray(HttpServletRequest request) {
        String roles = getAuthorisedRoles(request);
        if (roles == null || roles.length() == 0) {
            return new String[0];
        }

        // roles are space separated list of authorized roles
        return roles.split(" ");
    }

    public static boolean isRoleAuthorised(HttpServletRequest request) {
        String[] roles = getAuthorisedRolesArray(request);
        if (roles.length == 0) {
            return false;
        }

        return ArrayUtils.contains(roles, AuthenticationHelper.ADMIN_SCOTTISH_BANKRUPT_OFFICER_ROLE);
    }

    private static String getRequestHeader(HttpServletRequest request, String header) {
        return request == null ? null : request.getHeader(header);
    }
}
