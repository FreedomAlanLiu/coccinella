package org.daybreak.coccinella.app.admin;

import org.daybreak.coccinella.app.BaseController;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.service.AicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alan on 14-3-14.
 */
@Controller
@RequestMapping("/admin/aics")
public class AicController extends BaseController {

    @Inject
    private AicService aicService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("aics", aicService.loadAics());
        return "admin/aics/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String _new(Model model) {
        model.addAttribute("aic", new AIC());
        return "admin/aics/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid AIC aic, Model model) {
        AIC newAic = aicService.saveAic(aic);
        model.addAttribute("aic", newAic);
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("aic", aicService.loadAic(id));
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("aic", aicService.loadAic(id));
        return "admin/aics/edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String update(@PathVariable("id") long id, @Valid AIC aic, Model model) {
        AIC newAic = aicService.loadAic(id);
        newAic.setName(aic.getName());
        newAic.setProvince(aic.getProvince());
        newAic.setWebsite(aic.getWebsite());
        newAic.setMaxFrequency(aic.getMaxFrequency());
        model.addAttribute("aic", aicService.saveAic(newAic));
        return "admin/aics/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") long id, Model model) {
        aicService.deleteAic(id);
        model.addAttribute("aics", aicService.loadAics());
        return "admin/aics/index";
    }

    @RequestMapping(value = "/{aicId}/crawlers/new", method = RequestMethod.GET)
    public String newCrawler(Model model) {
        model.addAttribute("crawler", new Crawler());
        return "admin/aics/crawlers/new";
    }

    @RequestMapping(value = "/{aicId}/crawlers", method = RequestMethod.POST)
    public String createCrawler(@PathVariable long aicId, @Valid Crawler crawler, Model model) {
        AIC aic = aicService.loadAic(aicId);
        aic.getCrawlers().add(crawler);
        AIC newAic = aicService.saveAic(aic);
        model.addAttribute("aic", newAic);
        return "admin/aics/view";
    }
}
