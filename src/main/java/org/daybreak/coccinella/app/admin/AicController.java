package org.daybreak.coccinella.app.admin;

import org.daybreak.coccinella.app.BaseController;
import org.daybreak.coccinella.domain.service.AicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alan on 14-3-14.
 */
@Controller
public class AicController extends BaseController {

    @Inject
    AicService aicService;

    @RequestMapping(value = "/admin/aics", method = {RequestMethod.GET, RequestMethod.POST})
    public String aics(HttpServletRequest request, Model model) {
        model.addAttribute("aics", aicService.loadAics());
        return "admin/aics";
    }
}
