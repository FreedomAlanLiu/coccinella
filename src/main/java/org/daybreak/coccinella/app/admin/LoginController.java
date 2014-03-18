package org.daybreak.coccinella.app.admin;

import org.daybreak.coccinella.app.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("_csrf", getCsrfToken(request, response));
        return "admin/login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/admin/aics";
    }
}
