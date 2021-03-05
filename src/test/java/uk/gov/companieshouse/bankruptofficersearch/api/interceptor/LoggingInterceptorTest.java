package uk.gov.companieshouse.bankruptofficersearch.api.interceptor;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import uk.gov.companieshouse.logging.util.LogContextProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoggingInterceptorTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoggingInterceptor loggingInterceptor;

    @BeforeEach
    void setUp() {
        when(httpServletRequest.getSession()).thenReturn(session);
    }

    @Test
    @DisplayName("Tests the interceptor - preHandle()")
    void preHandle() throws JSONException {
        assertTrue(loggingInterceptor.preHandle(httpServletRequest, httpServletResponse, new Object()));
        verify(session, times(1)).setAttribute(eq(LogContextProperties.START_TIME_KEY.value()), anyLong());
    }

    @Test
    @DisplayName("Tests the interceptor - postHandle()")
    void postHandle() throws JSONException {
        long startTime = System.currentTimeMillis();
        when(session.getAttribute(LogContextProperties.START_TIME_KEY.value())).thenReturn(startTime);
        when(httpServletResponse.getStatus()).thenReturn(200);
        loggingInterceptor.postHandle(httpServletRequest, httpServletResponse, new Object(), new ModelAndView());
        verify(session, times(1)).getAttribute(LogContextProperties.START_TIME_KEY.value());
    }
}
