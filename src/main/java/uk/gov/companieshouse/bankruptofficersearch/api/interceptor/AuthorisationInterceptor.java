package uk.gov.companieshouse.bankruptofficersearch.api.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import uk.gov.companieshouse.bankruptofficersearch.api.util.AuthenticationHelper;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static uk.gov.companieshouse.bankruptofficersearch.api.BankruptOfficerSearchApiApplication.APPLICATION_NAME_SPACE;

@Component
public class AuthorisationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAME_SPACE);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        final boolean authUserHasBadosLookupRole = AuthenticationHelper.isRoleAuthorised(request);

        if(!authUserHasBadosLookupRole) {
            LOGGER.debugRequest(request, "UserAuthenticationInterceptor error: no correct role", null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        final String identityType = AuthenticationHelper.getAuthorisedIdentityType(request);

        if (!AuthenticationHelper.OAUTH2_IDENTITY_TYPE.equals(identityType)) {
            LOGGER.debugRequest(request, "UserAuthenticationInterceptor error: no correct authorised identity type", null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        final String identity = AuthenticationHelper.getAuthorisedIdentity(request);

        if (identity == null || identity.isEmpty()) {
            LOGGER.debugRequest(request, "UserAuthenticationInterceptor error: no authorised identity", null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return validateOAuth2Identity(request, response, identity);
    }

    private boolean validateOAuth2Identity(HttpServletRequest request, HttpServletResponse response, String identity) {
        request.setAttribute("user_id", identity);

        final String authorisedUser = AuthenticationHelper.getAuthorisedUser(request);

        if (authorisedUser == null || authorisedUser.trim().length() == 0) {
            LOGGER.debugRequest(request, "AuthorisationInterceptor error: no authorised user", null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // decode user details and set on session
        String[] userDetails = authorisedUser.split(";");
        if (indexExists(userDetails, 0)) {
            request.setAttribute("user_email", userDetails[0].trim());
        }
        if (indexExists(userDetails, 1)) {
            request.setAttribute("user_forename", getValue(userDetails[1]));
        }
        if (indexExists(userDetails, 2)) {
            request.setAttribute("user_surname", getValue(userDetails[2]));
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // cleanup request attributes to ensure user details are never leaked into another request
        request.setAttribute("user_id", null);
        request.setAttribute("user_email", null);
        request.setAttribute("user_forename", null);
        request.setAttribute("user_surname", null);
    }

    private String getValue(String value) {
        String[] returnValue = value.split("=");
        return indexExists(returnValue, 1) ? returnValue[1] : null;
    }

    private boolean indexExists(final String[] list, final int index) {
        return index >= 0 && index < list.length;
    }
}
