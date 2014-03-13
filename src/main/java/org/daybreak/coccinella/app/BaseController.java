package org.daybreak.coccinella.app;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alan on 14-3-14.
 */
public class BaseController {

    protected CsrfToken getCsrfToken(HttpServletRequest request, HttpServletResponse response, HttpSessionCsrfTokenRepository csrfTokenRepository) {
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
        if (csrfToken == null) {
            csrfToken = csrfTokenRepository.generateToken(request);
            csrfTokenRepository.saveToken(csrfToken, request, response);
        }
        return csrfToken;
    }
}
