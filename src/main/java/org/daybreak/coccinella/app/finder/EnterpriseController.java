package org.daybreak.coccinella.app.finder;

import org.daybreak.coccinella.domain.model.Condition;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.service.AicService;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by Alan on 14-3-16.
 */
@Controller
public class EnterpriseController {

    @Inject
    private AicService aicService;

    @Inject
    private EnterpriseService enterpriseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("aics", aicService.loadAics());
        model.addAttribute("condition", new Condition());
        return "finder/index";
    }

    @RequestMapping(value = "/enterprises", method = {RequestMethod.GET, RequestMethod.POST})
    public String find(@RequestParam(value = "province") String province,
                       @RequestParam(value = "enterpriseName") String enterpriseName,
                       @RequestParam(value = "cache") boolean cache,
                       @RequestParam(value = "p") int p,
                       @Valid Condition condition,
                       Model model) {
        if (condition == null) {
            condition = new Condition();
            condition.setProvince(province);
            condition.setEnterpriseName(enterpriseName);
            condition.setCache(cache);
        }
        Page<Enterprise> enterprise = enterpriseService.searchEnterprises(condition.getProvince(),
                condition.getEnterpriseName(), condition.isCache(), p < 0 ? 0 : p, 10);
        model.addAttribute("enterprises", enterprise);
        model.addAttribute("condition", condition);
        return "finder/enterprises";
    }
}
