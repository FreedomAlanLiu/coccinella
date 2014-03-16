package org.daybreak.coccinella.app.finder;

import org.daybreak.coccinella.domain.model.Condition;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.service.AicService;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by Alan on 14-3-16.
 */
@Controller
public class FinderController {

    @Inject
    private AicService aicService;

    @Inject
    private EnterpriseService enterpriseService;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Model model) {
        model.addAttribute("aics", aicService.loadAics());
        model.addAttribute("condition", new Condition());
        return "finder/finder";
    }

    @RequestMapping(value = "/enterprises", method = {RequestMethod.GET, RequestMethod.POST})
    public String find(@Valid Condition condition, Model model) {

        enterpriseService.searchEnterprises(condition.getProvince(), condition.getEnterpriseName(), 0, 20);

        model.addAttribute("aics", aicService.loadAics());
        model.addAttribute(new Condition());
        return "finder/finder";
    }
}
