package org.daybreak.coccinella.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alan on 14-3-14.
 */
public class BaseController {

    protected static final Logger logger = LoggerFactory
            .getLogger(BaseController.class);

    protected CsrfToken getCsrfToken(HttpServletRequest request, HttpServletResponse response, HttpSessionCsrfTokenRepository csrfTokenRepository) {
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
        if (csrfToken == null) {
            csrfToken = csrfTokenRepository.generateToken(request);
            csrfTokenRepository.saveToken(csrfToken, request, response);
        }
        return csrfToken;
    }
}
